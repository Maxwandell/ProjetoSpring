package com.max.projeto.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa FIsíca"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String description;
	
	private TipoCliente(int cod, String desc) {
		this.cod = cod;
		this.description = desc;
		
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescriçao() {
		return description;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido" + cod);
	}
}
