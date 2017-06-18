package com.siscond.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.siscond.modelo.Reservas;
import com.siscond.util.Util;

public class ReservasDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;

	//Consulta se já existe no BD
	public int consultaDtRes(Date data){
		int retorno = 0;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_reservas WHERE  data_reserva = "+data);
			if(rs.next()){
				//Data de Reserva ja cadastrada
				retorno = 1;
			}else{
				//Data de Reserva não encontrada
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e){
			return retorno;
		}
	}

	//Consulta se já existe no BD
	public int consultaHoraInRes(String horaInicial){
		int retorno = 0;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_reservas WHERE  horario_inicial = '"+horaInicial+"'");
			if(rs.next()){
				//Data de Reserva ja cadastrada
				retorno = 1;
			}else{
				//Data de Reserva não encontrada
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e){
			return retorno;
		}
	}

	//Consulta se já existe no BD
	public int consultaHoraFimRes(String horaFinal){
		int retorno = 0;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_reservas WHERE  horario_final = '"+horaFinal+"'");
			if(rs.next()){
				//Data de Reserva ja cadastrada
				retorno = 1;
			}else{
				//Data de Reserva não encontrada
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e){
			return retorno;
		}
	}

	//Inclusão no BD
	public int incluiReserva(Reservas reserva){
		int retorno = 0;
		String sql= null;
		//Verifica se Apartamento já esta cadastrado
		if(this.consultaDtRes(reserva.getData_reserva())==1 
				&& this.consultaHoraInRes(reserva.getHorario_inicial())==1
				&& this.consultaHoraFimRes(reserva.getHorario_final())==1){
			retorno = 2;
		}else{
			try{
				sql = "INSERT INTO tb_reservas(data_reserva, horario_inicial, horario_final, num_apto) VALUES( '";
				sql += Util.rsDataBD(reserva.getData_reserva())+"', '"+reserva.getHorario_inicial()+"', '";
				sql += reserva.getHorario_final()+"', "+reserva.getNum_apto()+")";
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
	public boolean alteraReserva(Reservas reservas){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "UPDATE tb_reservas SET ";
			sql += "data_reserva= '"+Util.rsDataBD(reservas.getData_reserva())+"', ";
			sql += "horario_inicial= '"+reservas.getHorario_inicial()+"', ";
			sql += "horario_final= '"+reservas.getHorario_final()+"', ";
			sql += "num_apto= "+reservas.getNum_apto()+", ";
			sql += " WHERE cod_reserva = "+reservas.getCod_reserva();
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
	public boolean excluiRervas(Reservas reserva){
		boolean retorno = false;
		String sql = null;
		try{
			sql ="DELETE FROM tb_reservas WHERE cod_reserva = "+reserva.getCod_reserva();
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
	public ArrayList<Reservas> consultaResNumApto (int numero){
		Reservas r = new Reservas();
		String sql = null;
		ArrayList<Reservas> aL = new ArrayList<Reservas>();
		try{
			sql = "SELECT * FROM tb_reservas WHERE num_apto = "+numero;
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				r = new Reservas();
				r.setCod_reserva(rs.getInt("cod_reserva"));
				r.setData_reserva(Util.rsData(rs.getString("data_reserva")));
				r.setHorario_inicial(rs.getString("horario_inicial"));
				r.setHorario_final(rs.getString("horario_final"));
				r.setCod_reserva(rs.getInt("num_apto"));
				aL.add(r);
			}
		}catch (SQLException e){
			return null;
		}
		return aL;
	}

	//Consulta e lista pela Data da Reserva
	public ArrayList<Reservas> consultaData (Date data){
		Reservas r = new Reservas();
		String sql = null;
		ArrayList<Reservas> aL = new ArrayList<Reservas>();
		try{
			if (data.equals(null) || data.equals("")){
				sql = "SELECT * FROM tb_reservas ORDER BY data_reserva";
			}else{
				sql = "SELECT * FROM tb_reservas WHERE data_reserva '"+Util.rsDataBD(data)+"' ORDER BY data_reserva";
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()){
				r = new Reservas();
				r.setCod_reserva(rs.getInt("cod_reserva"));
				r.setData_reserva(Util.rsData(rs.getString("data_reserva")));
				r.setHorario_inicial(rs.getString("horario_inicial"));
				r.setHorario_final(rs.getString("horario_final"));
				r.setCod_reserva(rs.getInt("num_apto"));
				aL.add(r);
			}
		}catch (SQLException e){
			return null;
		}
		return aL;
	}
}
