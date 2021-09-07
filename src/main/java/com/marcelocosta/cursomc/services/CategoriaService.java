package com.marcelocosta.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelocosta.cursomc.domain.Categoria;
import com.marcelocosta.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	//esta notacao faz a o estanciamento automatico
	@Autowired
	private CategoriaRepository repo;
	
	
	//operacao para buscar a categoria por codigo
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		}



}
