package com.marcelocosta.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcelocosta.cursomc.domain.Categoria;
import com.marcelocosta.cursomc.domain.Produto;
import com.marcelocosta.cursomc.repositories.CategoriaRepository;
import com.marcelocosta.cursomc.repositories.ProdutoRepository;
import com.marcelocosta.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	//esta notacao faz a o estanciamento automatico
	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//operacao para buscar a categoria por codigo
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encpntrado! ID: " + id + ", Tipo " +
				Produto.class.getName()));
		}
	
		//
	
		public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
			PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
			//busca paginada
			
			List<Categoria> categorias = categoriaRepository.findAllById(ids);
			
			return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
			
			
		}



}
