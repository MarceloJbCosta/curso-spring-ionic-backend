package com.marcelocosta.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcelocosta.cursomc.domain.Categoria;
import com.marcelocosta.cursomc.dto.CategoriaDTO;
import com.marcelocosta.cursomc.repositories.CategoriaRepository;
import com.marcelocosta.cursomc.services.exception.DataIntegrityException;
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
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma Categoria que possui produtos");
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
		
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	//metodo auxiliar que instacia um categoria a partir de um dto
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
		
	}
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

	
	

}
