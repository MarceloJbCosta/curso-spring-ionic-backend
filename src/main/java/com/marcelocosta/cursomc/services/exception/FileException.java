package com.marcelocosta.cursomc.services.exception;

public class FileException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	//construtor
	public FileException(String msg) {
		
		super(msg);
	}
	
	
	//outra sobregarda do contrutor que vai receber a msg e outra exececao que foi a causa q do que aconteceu antes
	public FileException(String msg, Throwable cause) {
		
		super(msg, cause);
		
	}

}
