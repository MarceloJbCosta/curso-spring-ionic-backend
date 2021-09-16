package com.marcelocosta.cursomc.services.exception;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	//construtor
	public DataIntegrityException(String msg) {
		
		super(msg);
	}
	
	
	//outra sobregarda do contrutor que vai receber a msg e outra exececao que foi a causa q do que aconteceu antes
	public DataIntegrityException(String msg, Throwable cause) {
		
		super(msg, cause);
		
	}

}
