package com.marcelocosta.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcelocosta.cursomc.services.DBService;
import com.marcelocosta.cursomc.services.EmailService;
import com.marcelocosta.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		
		dbService.instantiateDatabase();
		
		return true;
		
	}
	
	//@Bean deixa o metodo com essanotacao deixamos ele disponivel como um componente no sistema
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
