package com.marcelocosta.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcelocosta.cursomc.domain.Cliente;
import com.marcelocosta.cursomc.dto.ClienteDTO;
import com.marcelocosta.cursomc.repositories.ClienteRepository;
import com.marcelocosta.cursomc.services.exception.DataIntegrityException;
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
	//mudancas para buscar do banca de daods os datos que nao serao alterados
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir porque há entidades reslacionadas");
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	//metodo auxiliar que instacia um categoria a partir de um dto
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		
	}
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());	
	}


}
