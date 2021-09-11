package com.marcelocosta.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcelocosta.cursomc.domain.Categoria;
import com.marcelocosta.cursomc.domain.Cidade;
import com.marcelocosta.cursomc.domain.Estado;
import com.marcelocosta.cursomc.domain.Produto;
import com.marcelocosta.cursomc.repositories.CategoriaRepository;
import com.marcelocosta.cursomc.repositories.CidadeRepository;
import com.marcelocosta.cursomc.repositories.EstadoRepository;
import com.marcelocosta.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRpository;
	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 =  new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00 );
		Produto p3 = new Produto(null, "Mouse", 80.00); 
		
		//associar a categoria ao produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//associar produto a categoria
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		//adicionamdo os iten da ctaegoria ao bd
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		//add os itens de produto ao bd
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		//salvando estado e cidade no banco
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRpository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		
		
		
		
		
	}

}
