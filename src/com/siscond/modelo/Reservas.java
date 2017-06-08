package com.siscond.modelo;

import java.util.Date;

public class Reservas {
	private int cod_reserva;
	private Date data_reserva;
	private String horario_inicial;
	private String horario_final;
	private int num_apto;
	
	public int getCod_reserva() {
		return cod_reserva;
	}
	public void setCod_reserva(int cod_reserva) {
		this.cod_reserva = cod_reserva;
	}
	public Date getData_reserva() {
		return data_reserva;
	}
	public void setData_reserva(Date data_reserva) {
		this.data_reserva = data_reserva;
	}
	public String getHorario_inicial() {
		return horario_inicial;
	}
	public void setHorario_inicial(String horario_inicial) {
		this.horario_inicial = horario_inicial;
	}
	public String getHorario_final() {
		return horario_final;
	}
	public void setHorario_final(String horario_final) {
		this.horario_final = horario_final;
	}
	public int getNum_apto() {
		return num_apto;
	}
	public void setNum_apto(int num_apto) {
		this.num_apto = num_apto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_reserva;
		result = prime * result + ((data_reserva == null) ? 0 : data_reserva.hashCode());
		result = prime * result + ((horario_final == null) ? 0 : horario_final.hashCode());
		result = prime * result + ((horario_inicial == null) ? 0 : horario_inicial.hashCode());
		result = prime * result + num_apto;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservas other = (Reservas) obj;
		if (cod_reserva != other.cod_reserva)
			return false;
		if (data_reserva == null) {
			if (other.data_reserva != null)
				return false;
		} else if (!data_reserva.equals(other.data_reserva))
			return false;
		if (horario_final == null) {
			if (other.horario_final != null)
				return false;
		} else if (!horario_final.equals(other.horario_final))
			return false;
		if (horario_inicial == null) {
			if (other.horario_inicial != null)
				return false;
		} else if (!horario_inicial.equals(other.horario_inicial))
			return false;
		if (num_apto != other.num_apto)
			return false;
		return true;
	}
	
}
