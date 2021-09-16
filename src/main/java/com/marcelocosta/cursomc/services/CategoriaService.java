package com.marcelocosta.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelocosta.cursomc.domain.Categoria;
import com.marcelocosta.cursomc.repositories.CategoriaRepository;
import com.marcelocosta.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	//esta notacao faz a o estanciamento automatico
	@Autowired
	private CategoriaRepository repo;
	
	
	//operacao para buscar a categoria por codigo
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encpntrado! ID: " + id + ", Tipo " +
				Categoria.class.getName()));
		}
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		
		return repo.save(obj);

	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}


}
