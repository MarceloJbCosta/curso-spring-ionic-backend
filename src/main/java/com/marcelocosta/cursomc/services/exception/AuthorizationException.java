package com.marcelocosta.cursomc.services.exception;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	//construtor
	public AuthorizationException(String msg) {
		
		super(msg);
	}
	
	
	//outra sobregarda do contrutor que vai receber a msg e outra exececao que foi a causa q do que aconteceu antes
	public AuthorizationException(String msg, Throwable cause) {
		
		super(msg, cause);
		
	}

}
