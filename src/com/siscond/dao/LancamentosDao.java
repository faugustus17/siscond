package com.siscond.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.siscond.modelo.Lancamentos;
import com.siscond.util.Util;

public class LancamentosDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;
	
	/**
	 * Consulta se lan�amento ja existe
	 * @param desc
	 * @return
	 */
	//Consulta se j� existe no BD
	public int consultaLcto(String desc){
		int retorno = 0;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_apartamentos WHERE  descricao_lancamento = '"+desc+"'");
			if(rs.next()){
				//Lan�amento ja cadastrado
				retorno = 1;
			}else{
				//Apartamento n�o encontrado
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e){
			return retorno;
		}
	}
	
	/**
	 * Inclui no BD
	 * @param lcto
	 * @return
	 */
	//Inclus�o no BD
	public int incluiLcto(Lancamentos lcto){
		int retorno = 0;
		String sql= null;
		//Verifica se Lan�amento j� esta cadastrado
		if(this.consultaLcto(lcto.getDescricao_lancamento())==1){
			retorno = 2;
		}else{
			try{
				sql = "INSERT INTO tb_lancamentos(descricao_lancamento, tipo_lancamento) VALUES ('";
				sql += lcto.getDescricao_lancamento() +"', '"+lcto.getTipo_lancamento()+"')";
				st = conn.createStatement();
				int rst = st.executeUpdate(sql);
				if(rst == 1){
					//Cadastro efetuado com sucesso
					retorno = 1;
				}else{
					//Cadastro n�o efetuado
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
	 * @param lcto
	 * @return
	 */
	//Altera��o no BD
	public boolean alteraLcto(Lancamentos lcto){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "UPDATE tb_lancamentos SET ";
			sql += "descricao_lancamento= '"+lcto.getDescricao_lancamento()+"', ";
			sql += "tipo_lancamento= '"+lcto.getTipo_lancamento()+"'";
			sql += " WHERE cod_lancamento = "+lcto.getCod_lancamento();
			st = conn.createStatement();
			int rst = st.executeUpdate(sql);
			if(rst == 1){
				//Altera��o efetuada com sucesso
				retorno = true;
			}else{
				//ALtera��o n�o efetuada
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
	 * @param lcto
	 * @return
	 */
	//Exclus�o no BD
	public boolean excluiLcto(Lancamentos lcto){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "DELETE FROM tb_lancamentos WHERE cod_lancamento = "+lcto.getCod_lancamento();
			st = conn.createStatement();
			int rst = st.executeUpdate(sql);
			if (rst == 1){
				//Exclus�o efetuada com sucesso
				retorno = true;
			}else{
				//Exclus�o n�o efetuada
				retorno = false;
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return retorno;
		}
		return retorno;	
	}
	
	/**
	 * Consulta e lista pelo tipo de lancamento
	 * @param tipo
	 * @return
	 */
	//Consulta e lista pelo Tipo do Lancamento
	public ArrayList<Lancamentos> consultaTipoLcto (String tipo){
		Lancamentos l = new Lancamentos();
		String sql = null;
		ArrayList<Lancamentos> aL = new ArrayList<Lancamentos>();
		try{
			sql = "SELECT * FROM tb_lancamentos WHERE tipo_lancamento = '"+tipo+"'";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				l = new Lancamentos();
				l.setCod_lancamento(rs.getInt("cod_lancamento"));
				l.setDescricao_lancamento(rs.getString("descricao_lancamento"));
				l.setTipo_lancamento(rs.getString("tipo_lancamento"));
				aL.add(l);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aL;
	}
	
	/**
	 * Consulta e lista pela descri��o do lan�amento
	 * @param desc
	 * @return
	 */
	//Consulta e lista pela Descri��o do Lan�amento
	public ArrayList<Lancamentos> consultaDescLcto (String desc){
		Lancamentos l = new Lancamentos();
		String sql = null;
		ArrayList<Lancamentos> aL = new ArrayList<Lancamentos>();
		try{
			if (desc.equals("") || desc.length() == 0 || desc.isEmpty()){
				sql = "SELECT * FROM tb_lancamentos ORDER BY descricao_lancamento";
			}else{
				sql = "SELECT * FROM tb_lancamentos WHERE descricao_lancamento LIKE ";
				sql += "'%"+desc+"%' ORDER BY descricao_lancamento";
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				l = new Lancamentos();
				l.setCod_lancamento(rs.getInt("cod_lancamento"));
				l.setDescricao_lancamento(rs.getString("descricao_lancamento"));
				l.setTipo_lancamento(rs.getString("tipo_lancamento"));
				aL.add(l);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aL;
	}
	
	//Consulta e lista Lancamento
	public ArrayList<Lancamentos> listaLcto (){
		Lancamentos l = new Lancamentos();
		ArrayList<Lancamentos> aL = new ArrayList<Lancamentos>();
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_lancamentos ORDER BY descricao_lancamento");
			while (rs.next()){
				l = new Lancamentos();
				l.setCod_lancamento(rs.getInt("cod_lancamento"));
				l.setDescricao_lancamento(rs.getString("descricao_lancamento"));
				l.setTipo_lancamento(rs.getString("tipo_lancamento"));
				aL.add(l);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aL;
	}
	
	//Consulta e lista Lancamento
	public Lancamentos consultaPorIdLcto(int codigo){
		Lancamentos l = new Lancamentos();
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_lancamentos WHERE cod_lancamento = "+codigo);
			if(rs.next()){
				l = new Lancamentos();
				l.setCod_lancamento(rs.getInt("cod_lancamento"));
				l.setDescricao_lancamento(rs.getString("descricao_lancamento"));
				l.setTipo_lancamento(rs.getString("tipo_lancamento"));
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return l;
	}
	
	//Consulta e lista Lancamento
	public Lancamentos consultaPorLcto(String descricao){
		Lancamentos l = new Lancamentos();
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_lancamentos WHERE descricao_lancamento = '"+descricao+"'");
			if(rs.next()){
				l = new Lancamentos();
				l.setCod_lancamento(rs.getInt("cod_lancamento"));
				l.setDescricao_lancamento(rs.getString("descricao_lancamento"));
				l.setTipo_lancamento(rs.getString("tipo_lancamento"));
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return l;
	}
}
