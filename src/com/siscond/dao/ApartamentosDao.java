package com.siscond.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import java.sql.Statement;
import com.siscond.modelo.Apartamentos;

public class ApartamentosDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;
	
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
			}
			return retorno;
		}catch (SQLException e){
			return retorno;
		}
	}
	
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
				return retorno;
			}
		}
		return retorno;
	}
	
	//Alteração no BD
	
	
}
