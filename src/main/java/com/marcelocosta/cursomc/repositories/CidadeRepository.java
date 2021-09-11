package com.marcelocosta.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelocosta.cursomc.domain.Cidade;



@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	

}
