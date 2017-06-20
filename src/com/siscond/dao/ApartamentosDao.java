package com.siscond.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import com.siscond.modelo.Apartamentos;
import com.siscond.util.Util;

public class ApartamentosDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;
	
	/**
	 * Consulta se já existe no BD
	 * @param num_apto
	 * @return
	 */
	//Consulta se já existe no BD
	public int consultaApto(int num_apto){
		int retorno = 0;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_apartamentos WHERE  num_apto = "+num_apto);
			if(rs.next()){
				//Apartamento ja cadastrado
				retorno = 1;
			}else{
				//Apartamento não encontrado
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e){
			return retorno;
		}
	}
	
	/**
	 * Inclui no BD
	 * @param apto
	 * @return
	 */
	//Inclusão no BD
	public int incluiApto(Apartamentos apto){
		int retorno = 0;
		String sql= null;
		//Verifica se Apartamento já esta cadastrado
		if(this.consultaApto(apto.getNum_apto())==1){
			retorno = 2;
		}else{
			try{
				sql = "INSERT INTO tb_apartamentos(num_apto, nome_titular, telefone_titular) VALUES( ";
				sql += apto.getNum_apto() +", '"+apto.getNome_titular()+"', '"+apto.getTelefone_titular()+"')";
				st = conn.createStatement();
				int rst = st.executeUpdate(sql);
				if(rst == 1){
					//Cadastro efetuado com sucesso
					retorno = 1;
				}else{
					//Cadastro não efetuado
					retorno = 0;
				}
			}catch (SQLException e){
				Util.mensagemErro(e.getMessage());
				return retorno;
			}
		}
		return retorno;
	}
	
	/**
	 * Altera no BD
	 * @param apto
	 * @return
	 */
	//Alteração no BD
	public boolean alteraApto(Apartamentos apto){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "UPDATE tb_apartamentos SET ";
			sql += "nome_titular= '"+apto.getNome_titular()+"', ";
			sql += "telefone_titular= '"+apto.getTelefone_titular()+"'";
			sql += " WHERE num_apto = "+apto.getNum_apto();
			st = conn.createStatement();
			int rst = st.executeUpdate(sql);
			if(rst == 1){
				//Alteração efetuada com sucesso
				retorno = true;
			}else{
				//ALteração não efetuada
				retorno = false;
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return retorno;
		}
		return retorno;	
	}
	
	/**
	 * Exclui no BD
	 * @param apto
	 * @return
	 */
	//Exclusão no BD
	public boolean excluiApto(Apartamentos apto){
		boolean retorno = false;
		String sql = null;
		try{
			sql =" DELETE FROM tb_apartamentos WHERE num_apto = "+apto.getNum_apto();
			st = conn.createStatement();
			int rst = st.executeUpdate(sql);
			if (rst == 1){
				//Exclusão efetuada com sucesso
				retorno = true;
			}else{
				//Exclusão não efetuada
				retorno = false;
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return retorno;
		}
		return retorno;	
	}
	
	/**
	 * Consulta e lista pelo numero do apartamento
	 */
	//Consulta e lista pelo Numero do Apartamento
	public ArrayList<Apartamentos> consultaNumApto (int numero){
		Apartamentos a = new Apartamentos();
		String sql = null;
		ArrayList<Apartamentos> aLAp = new ArrayList<Apartamentos>();
		try{
			sql = "SELECT * FROM tb_apartamentos WHERE num_apto = "+numero;
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				a = new Apartamentos();
				a.setNum_apto(rs.getInt("num_apto"));
				a.setNome_titular(rs.getString("nome_titular"));
				a.setTelefone_titular(rs.getString("telefone_titular"));
				aLAp.add(a);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aLAp;
	}
	
	/**
	 * Consulta e lista por Apartamento
	 * @param numero
	 * @return
	 */
	//Consulta e lista por Numero de Apartamento
	public Apartamentos consultaPorNumApto(int numero){
		Apartamentos a = new Apartamentos();
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_apartamentos WHERE num_apto = "+numero);
			if(rs.next()){
				a.setNum_apto(rs.getInt("num_apto"));
				a.setNome_titular(rs.getString("nome_titular"));
				a.setTelefone_titular(rs.getString("telefone_titular"));
			}
		}catch (SQLException e){
			Util.mensagemErro("Erro: "+e.getMessage());
			return null;
		}
		return a;
	}
	
	/**
	 * Consulta e lista por nome do proprietário
	 * @param nome
	 * @return
	 */
	//Consulta e lista pelo Nome do Proprietário
	public ArrayList<Apartamentos> consultaNomeTitular (String nome){
		Apartamentos a = new Apartamentos();
		String sql = null;
		ArrayList<Apartamentos> aLAp = new ArrayList<Apartamentos>();
		try{
			if (nome.equals("") || nome.length() == 0 || nome.isEmpty()){
				sql = "SELECT * FROM tb_apartamentos ORDER BY nome_titular";
			}else{
				sql = "SELECT * FROM tb_apartamentos WHERE nome_titular LIKE ";
				sql += "'%"+nome+"%' ORDER BY nome_titular";
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				a = new Apartamentos();
				a.setNum_apto(rs.getInt("num_apto"));
				a.setNome_titular(rs.getString("nome_titular"));
				a.setTelefone_titular(rs.getString("telefone_titular"));
				aLAp.add(a);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aLAp;
	}
	
	/**
	 * Consulta e lista por apartamento
	 * @return
	 */
	//Consulta e lista Apartamento
	public ArrayList<Apartamentos> listaNumApto (){
		Apartamentos a = new Apartamentos();
		ArrayList<Apartamentos> aLAp = new ArrayList<Apartamentos>();
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_apartamentos ORDER BY num_apto");
			while (rs.next()){
				a = new Apartamentos();
				a.setNum_apto(rs.getInt("num_apto"));
				a.setNome_titular(rs.getString("nome_titular"));
				a.setTelefone_titular(rs.getString("telefone_titular"));
				aLAp.add(a);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aLAp;
	}
}
