package com.marcelocosta.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelocosta.cursomc.domain.Pedido;
import com.marcelocosta.cursomc.repositories.PedidoRepository;
import com.marcelocosta.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	//esta notacao faz a o estanciamento automatico
	@Autowired
	private PedidoRepository repo;
	
	
	//operacao para buscar a categoria por codigo
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encpntrado! ID: " + id + ", Tipo " +
				Pedido.class.getName()));
		}



}
