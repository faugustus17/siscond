package com.siscond.util;

public class MenuAuxiliar {
	public enum TipoPesquisaAp{
		SEL("Selecione"),
		PORNUMAP("N�mero do Apartamento"),
		PORNOME("Nome do Propriet�rio");
		
		private String label;
		
		TipoPesquisaAp(String label){
			this.label = label;
		}
		
		public String toString(){
			return label;
		}
	}

}
