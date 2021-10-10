package com.marcelocosta.cursomc.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),//EXIGENCIA DO SPRING SECURITY
	CLIENTE(2, "ROLE_CLIENTE");
	
	
	
	private int cod;
	private String descricao;
	
	//contrutor de tipo enumerado é private
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	//apenas get, depois que vc atribui os tipos enumerado vc nao pode mais mudar
	public  int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	//operacao que vai receber um codido e vai retornar o tipo enumerado
	
	public static Perfil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		//busca for especial cria um tipo X que vai percorere todos dos tiops
		//depois vai ser feito uma comparacp do codigo  com os tipos se for igal ele retorna 
		//se for deiferente ele ele retorna o erro
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}

}
