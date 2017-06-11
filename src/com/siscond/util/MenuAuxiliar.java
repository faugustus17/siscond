package com.siscond.util;

public class MenuAuxiliar {
	public enum TipoPesquisaAp{
		SEL("Selecione"),
		PORNUMAP("Número do Apartamento"),
		PORNOME("Nome do Proprietário");
		
		private String label;
		
		TipoPesquisaAp(String label){
			this.label = label;
		}
		
		public String toString(){
			return label;
		}
	}

}
