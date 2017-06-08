package com.siscond.modelo;

public class Lancamentos {
	private int cod_lancamento;
	private String descricao_lancamento;
	private String tipo_lancamento;
	
	public int getCod_lancamento() {
		return cod_lancamento;
	}
	public void setCod_lancamento(int cod_lancamento) {
		this.cod_lancamento = cod_lancamento;
	}
	public String getDescricao_lancamento() {
		return descricao_lancamento;
	}
	public void setDescricao_lancamento(String descricao_lancamento) {
		this.descricao_lancamento = descricao_lancamento;
	}
	public String getTipo_lancamento() {
		return tipo_lancamento;
	}
	public void setTipo_lancamento(String tipo_lancamento) {
		this.tipo_lancamento = tipo_lancamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_lancamento;
		result = prime * result + ((descricao_lancamento == null) ? 0 : descricao_lancamento.hashCode());
		result = prime * result + ((tipo_lancamento == null) ? 0 : tipo_lancamento.hashCode());
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
		Lancamentos other = (Lancamentos) obj;
		if (cod_lancamento != other.cod_lancamento)
			return false;
		if (descricao_lancamento == null) {
			if (other.descricao_lancamento != null)
				return false;
		} else if (!descricao_lancamento.equals(other.descricao_lancamento))
			return false;
		if (tipo_lancamento == null) {
			if (other.tipo_lancamento != null)
				return false;
		} else if (!tipo_lancamento.equals(other.tipo_lancamento))
			return false;
		return true;
	}
}
