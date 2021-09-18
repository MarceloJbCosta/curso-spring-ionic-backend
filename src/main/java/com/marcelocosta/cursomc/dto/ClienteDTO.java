package com.marcelocosta.cursomc.dto;


import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.marcelocosta.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatótio")
	@Length(min=5, max=120, message="O Tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	
	@NotEmpty(message="Preenchimento obrigatótio")
	@Email(message="Email invalido")
	private String email;
	
	public ClienteDTO() {
		
	}
	//recebe uma entidade clente e gera o deto
	public ClienteDTO(Cliente obj) {
		
		obj.getId();
		obj.getNome();
		obj.getEmail();
		
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
