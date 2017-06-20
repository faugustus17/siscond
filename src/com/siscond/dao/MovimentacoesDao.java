package com.siscond.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.siscond.modelo.Movimentacoes;
import com.siscond.util.Util;

public class MovimentacoesDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;

	//Inclusão no BD
	public int incluiMov(Movimentacoes mov){
		int retorno = 0;
		String sql= null;
		try{
			sql = "INSERT INTO tb_movimentacoes(data_movimento, valor, num_documento, num_apto, cod_lancamento) VALUES('";
			sql += Util.rsDataBD(mov.getData_movimentacao()) +"', '";
			sql += mov.getValor()+"', "+mov.getNum_documento() +" , " ;
			sql += mov.getNum_apto() +", "+ mov.getCod_lancamento()+")";
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
		return retorno;
	}

	//Alteração no BD
	public boolean alteraMov(Movimentacoes mov){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "UPDATE tb_movimentacoes SET ";
			sql += "data_movimento= '"+Util.rsDataBD(mov.getData_movimentacao()) +"', ";
			sql += "valor= '"+mov.getValor()+"', ";
			sql += "num_documento= "+mov.getNum_documento() +", ";
			sql += "num_apto= "+mov.getNum_apto() +", ";
			sql += "cod_lancamento= "+ mov.getCod_lancamento();
			sql += " WHERE cod_movimentacao= "+mov.getCod_movimentacao();
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

	//Exclusão no BD
	public boolean excluiMov(Movimentacoes mov){
		boolean retorno = false;
		String sql = null;
		try{
			sql ="DELETE FROM tb_movimentacoes WHERE cod_movimentacao = "+mov.getCod_movimentacao();
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

	//Consulta e lista pelo Numero do Apartamento
	public ArrayList<Movimentacoes> consultaMovNumApto (String numero){
		Movimentacoes m = new Movimentacoes();
		String sql = null;
		ArrayList<Movimentacoes> aL = new ArrayList<Movimentacoes>();
		try{
			if (numero.equals(null) || numero.equals("")){
				sql = "SELECT * FROM tb_movimentacoes ORDER BY cod_lancamento";
			}else{
				sql = "SELECT * FROM tb_movimentacoes WHERE num_apto = "+Integer.parseInt(numero);
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				m = new Movimentacoes();
				m.setCod_movimentacao(rs.getInt("cod_movimentacao"));
				m.setData_movimentacao(Util.rsData(rs.getString("data_movimento")));
				m.setValor(rs.getFloat("valor"));
				m.setNum_documento(rs.getInt("num_documento"));
				m.setNum_apto(rs.getInt("num_apto"));
				m.setCod_lancamento(rs.getInt("cod_lancamento"));
				aL.add(m);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aL;
	}
	
	//Consulta e lista pelo Codigo do Lançamento
	public ArrayList<Movimentacoes> consultaCodLcto (String numero){
		Movimentacoes m = new Movimentacoes();
		String sql = null;
		ArrayList<Movimentacoes> aL = new ArrayList<Movimentacoes>();
		try{
			if (numero.equals(null) || numero.equals("")){
				sql = "SELECT * FROM tb_movimentacoes ORDER BY cod_lancamento";
			}else{
				sql = "SELECT * FROM tb_movimentacoes WHERE cod_lancamento = "+Integer.parseInt(numero);
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				m = new Movimentacoes();
				m.setCod_movimentacao(rs.getInt("cod_movimentacao"));
				m.setData_movimentacao(Util.rsData(rs.getString("data_movimento")));
				m.setValor(rs.getFloat("valor"));
				m.setNum_documento(rs.getInt("num_documento"));
				m.setNum_apto(rs.getInt("num_apto"));
				m.setCod_lancamento(rs.getInt("cod_lancamento"));
				aL.add(m);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aL;
	}

	//Consulta e lista pela Data 
	public ArrayList<Movimentacoes> consultaData (String data){
		Movimentacoes m = new Movimentacoes();
		String sql = null;
		ArrayList<Movimentacoes> aL = new ArrayList<Movimentacoes>();
		try{
			if (data.equals(null) || data.equals("")){
				sql = "SELECT * FROM tb_movimentacoes ORDER BY data_movimentacao";
			}else{
				sql = "SELECT * FROM tb_movimentacoes WHERE data_movimentacao '"+Util.rsDataBD(Util.dataF(data))+"' ORDER BY data_movimentacao";
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				m = new Movimentacoes();
				m.setCod_movimentacao(rs.getInt("cod_movimentacao"));
				m.setData_movimentacao(Util.rsData(rs.getString("data_movimento")));
				m.setValor(rs.getFloat("valor"));
				m.setNum_documento(rs.getInt("num_documento"));
				m.setNum_apto(rs.getInt("num_apto"));
				m.setCod_lancamento(rs.getInt("cod_lancamento"));
				aL.add(m);
			}
		}catch (SQLException e){
			Util.mensagemErro(e.getMessage());
			return null;
		}
		return aL;
	}

}
