package com.marcelocosta.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcelocosta.cursomc.services.S3Service;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	

	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		s3Service.uploadFile("/Users/macbook/Desktop/Curso Spring/fotos/ana.jpg");

	}
	


}
