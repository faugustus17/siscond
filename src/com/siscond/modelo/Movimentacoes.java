package com.siscond.modelo;

import java.util.Date;

public class Movimentacoes {
	private int cod_movimentacao;
	private Date data_movimentacao;
	private float valor;
	private int num_documento;
	private int num_apto;
	private int cod_lancamento;
	
	public int getCod_movimentacao() {
		return cod_movimentacao;
	}
	public void setCod_movimentacao(int cod_movimentacao) {
		this.cod_movimentacao = cod_movimentacao;
	}
	public Date getData_movimentacao() {
		return data_movimentacao;
	}
	public void setData_movimentacao(Date data_movimentacao) {
		this.data_movimentacao = data_movimentacao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getNum_documento() {
		return num_documento;
	}
	public void setNum_documento(int num_documento) {
		this.num_documento = num_documento;
	}
	public int getNum_apto() {
		return num_apto;
	}
	public void setNum_apto(int num_apto) {
		this.num_apto = num_apto;
	}
	public int getCod_lancamento() {
		return cod_lancamento;
	}
	public void setCod_lancamento(int cod_lancamento) {
		this.cod_lancamento = cod_lancamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_lancamento;
		result = prime * result + cod_movimentacao;
		result = prime * result + ((data_movimentacao == null) ? 0 : data_movimentacao.hashCode());
		result = prime * result + num_apto;
		result = prime * result + num_documento;
		result = prime * result + Float.floatToIntBits(valor);
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
		Movimentacoes other = (Movimentacoes) obj;
		if (cod_lancamento != other.cod_lancamento)
			return false;
		if (cod_movimentacao != other.cod_movimentacao)
			return false;
		if (data_movimentacao == null) {
			if (other.data_movimentacao != null)
				return false;
		} else if (!data_movimentacao.equals(other.data_movimentacao))
			return false;
		if (num_apto != other.num_apto)
			return false;
		if (num_documento != other.num_documento)
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}
}
