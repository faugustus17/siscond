package com.siscond.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import com.mysql.jdbc.Connection;
import com.siscond.modelo.Movimentacoes;
import com.siscond.util.Util;

public class MovimentacoesDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;

	//Consulta se já existe no BD
	/*public int consultaApto(int num_apto){
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
	}*/

	//Inclusão no BD
	public int incluiApto(Movimentacoes mov){
		int retorno = 0;
		String sql= null;
		//Verifica se Apartamento já esta cadastrado
		/*if(this.consultaApto(mov.getNum_apto())==1){
			retorno = 2;
		}else{*/
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
				return retorno;
			}
		//}
		return retorno;
	}

	//Alteração no BD
	public boolean alteraMov(Movimentacoes mov){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "UPDATE tb_movimentacoes SET";
			sql += Util.rsDataBD(mov.getData_movimentacao()) +"', '";
			sql += mov.getValor()+"', "+mov.getNum_documento() +" , " ;
			sql += mov.getNum_apto() +", "+ mov.getCod_lancamento()+")";
			sql += " WHERE cod_movimentacao = "+mov.getCod_movimentacao();
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
			return retorno;
		}
		return retorno;	
	}

	//Exclusão no BD
	public boolean excluiApto(Movimentacoes mov){
		boolean retorno = false;
		String sql = null;
		try{
			sql =" DELETE FROM tb_movimentacoes WHERE cod_movimentacao = "+mov.getCod_movimentacao();
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
			return retorno;
		}
		return retorno;	
	}

	//Consulta e lista pelo Numero do Apartamento
	public ArrayList<Movimentacoes> consultaMovNumApto (int numero){
		Movimentacoes m = new Movimentacoes();
		String sql = null;
		ArrayList<Movimentacoes> aL = new ArrayList<Movimentacoes>();
		try{
			sql = "SELECT * FROM tb_movimentacoes WHERE num_apto = "+numero;
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				m = new Movimentacoes();
				m.setCod_lancamento(rs.getInt("cod_movimentacao"));
				m.setData_movimentacao(Util.rsData(rs.getString("nome_titular")));
				m.setValor(rs.getFloat("telefone_titular"));
				m.setNum_documento(rs.getInt("num_documento"));
				m.setNum_apto(rs.getInt("num_apto"));
				m.setCod_lancamento(rs.getInt("cod_lancamento"));
				aL.add(m);
			}
		}catch (SQLException e){
			return null;
		}
		return aL;
	}
	
	//Consulta e lista pelo Codigo do Lançamento
	public ArrayList<Movimentacoes> consultaCodLcto (int numero){
		Movimentacoes m = new Movimentacoes();
		String sql = null;
		ArrayList<Movimentacoes> aL = new ArrayList<Movimentacoes>();
		try{
			sql = "SELECT * FROM tb_movimentacoes WHERE cod_lancamento = "+numero;
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				m = new Movimentacoes();
				m.setCod_lancamento(rs.getInt("cod_movimentacao"));
				m.setData_movimentacao(Util.rsData(rs.getString("nome_titular")));
				m.setValor(rs.getFloat("telefone_titular"));
				m.setNum_documento(rs.getInt("num_documento"));
				m.setNum_apto(rs.getInt("num_apto"));
				m.setCod_lancamento(rs.getInt("cod_lancamento"));
				aL.add(m);
			}
		}catch (SQLException e){
			return null;
		}
		return aL;
	}

	//Consulta e lista pela Data 
	public ArrayList<Movimentacoes> consultaData (Date data){
		Movimentacoes m = new Movimentacoes();
		String sql = null;
		ArrayList<Movimentacoes> aL = new ArrayList<Movimentacoes>();
		try{
			if (data.equals(null) || data.equals("")){
				sql = "SELECT * FROM tb_movimentacoes ORDER BY data_movimentacao";
			}else{
				sql = "SELECT * FROM tb_movimentacoes WHERE data_movimentacao '"+Util.rsDataBD(data)+"' ORDER BY data_movimentacao";
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				m = new Movimentacoes();
				m.setCod_lancamento(rs.getInt("cod_movimentacao"));
				m.setData_movimentacao(Util.rsData(rs.getString("nome_titular")));
				m.setValor(rs.getFloat("telefone_titular"));
				m.setNum_documento(rs.getInt("num_documento"));
				m.setNum_apto(rs.getInt("num_apto"));
				m.setCod_lancamento(rs.getInt("cod_lancamento"));
				aL.add(m);
			}
		}catch (SQLException e){
			return null;
		}
		return aL;
	}

}
