package com.marcelocosta.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelocosta.cursomc.domain.Cliente;
import com.marcelocosta.cursomc.repositories.ClienteRepository;
import com.marcelocosta.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//esta notacao faz a o estanciamento automatico
	@Autowired
	private ClienteRepository repo;
	
	
	//operacao para buscar a categoria por codigo
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encpntrado! ID: " + id + ", Tipo " +
				Cliente.class.getName()));
		}



}
