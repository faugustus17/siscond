package com.siscond.modelo;

public class Apartamentos {
	private int num_apto;
	private String nome_titular;
	private String telefone_titular;
	
	public int getNum_apto() {
		return num_apto;
	}
	public void setNum_apto(int num_apto) {
		this.num_apto = num_apto;
	}
	public String getNome_titular() {
		return nome_titular;
	}
	public void setNome_titular(String nome_titular) {
		this.nome_titular = nome_titular;
	}
	public String getTelefone_titular() {
		return telefone_titular;
	}
	public void setTelefone_titular(String telefone_titular) {
		this.telefone_titular = telefone_titular;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome_titular == null) ? 0 : nome_titular.hashCode());
		result = prime * result + num_apto;
		result = prime * result + ((telefone_titular == null) ? 0 : telefone_titular.hashCode());
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
		Apartamentos other = (Apartamentos) obj;
		if (nome_titular == null) {
			if (other.nome_titular != null)
				return false;
		} else if (!nome_titular.equals(other.nome_titular))
			return false;
		if (num_apto != other.num_apto)
			return false;
		if (telefone_titular == null) {
			if (other.telefone_titular != null)
				return false;
		} else if (!telefone_titular.equals(other.telefone_titular))
			return false;
		return true;
	}
}
