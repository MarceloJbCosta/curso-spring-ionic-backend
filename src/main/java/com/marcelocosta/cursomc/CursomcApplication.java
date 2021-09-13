package com.marcelocosta.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcelocosta.cursomc.domain.Categoria;
import com.marcelocosta.cursomc.domain.Cidade;
import com.marcelocosta.cursomc.domain.Cliente;
import com.marcelocosta.cursomc.domain.Endereco;
import com.marcelocosta.cursomc.domain.Estado;
import com.marcelocosta.cursomc.domain.Pagamento;
import com.marcelocosta.cursomc.domain.PagamentoComBoleto;
import com.marcelocosta.cursomc.domain.PagamentoComCartao;
import com.marcelocosta.cursomc.domain.Pedido;
import com.marcelocosta.cursomc.domain.Produto;
import com.marcelocosta.cursomc.domain.enums.EstadoPagamento;
import com.marcelocosta.cursomc.domain.enums.TipoCliente;
import com.marcelocosta.cursomc.repositories.CategoriaRepository;
import com.marcelocosta.cursomc.repositories.CidadeRepository;
import com.marcelocosta.cursomc.repositories.ClienteRepository;
import com.marcelocosta.cursomc.repositories.EnderecoRepository;
import com.marcelocosta.cursomc.repositories.EstadoRepository;
import com.marcelocosta.cursomc.repositories.PagamentoRepository;
import com.marcelocosta.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	

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
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@email.com", "231233445", TipoCliente.PESSOAFISICA);
		
		//adidionar telefone
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38770121", cli1, c2);
		
		cli1.getEsderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Pedido ped1 = new Pedido(null, sdf.parse("20/08/2019 21:42"), cli1, e1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("28/10/2019 19:35"), cli1, e2);
		
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/10/2019 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		
	}

}
