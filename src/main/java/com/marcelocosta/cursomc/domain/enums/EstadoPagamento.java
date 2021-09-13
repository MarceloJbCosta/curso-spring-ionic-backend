package com.marcelocosta.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	
	
	private int cod;
	private String descricao;
	
	//contrutor de tipo enumerado é private
	
	private EstadoPagamento(int cod, String descricao) {
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
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		//busca for especial cria um tipo X que vai percorere todos dos tiops
		//depois vai ser feito uma comparacp do codigo  com os tipos se for igal ele retorna 
		//se for deiferente ele ele retorna o erro
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}

}
