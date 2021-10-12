package com.marcelocosta.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcelocosta.cursomc.domain.Categoria;
import com.marcelocosta.cursomc.domain.Cidade;
import com.marcelocosta.cursomc.domain.Cliente;
import com.marcelocosta.cursomc.domain.Endereco;
import com.marcelocosta.cursomc.domain.Estado;
import com.marcelocosta.cursomc.domain.ItemPedido;
import com.marcelocosta.cursomc.domain.Pagamento;
import com.marcelocosta.cursomc.domain.PagamentoComBoleto;
import com.marcelocosta.cursomc.domain.PagamentoComCartao;
import com.marcelocosta.cursomc.domain.Pedido;
import com.marcelocosta.cursomc.domain.Produto;
import com.marcelocosta.cursomc.domain.enums.EstadoPagamento;
import com.marcelocosta.cursomc.domain.enums.Perfil;
import com.marcelocosta.cursomc.domain.enums.TipoCliente;
import com.marcelocosta.cursomc.repositories.CategoriaRepository;
import com.marcelocosta.cursomc.repositories.CidadeRepository;
import com.marcelocosta.cursomc.repositories.ClienteRepository;
import com.marcelocosta.cursomc.repositories.EnderecoRepository;
import com.marcelocosta.cursomc.repositories.EstadoRepository;
import com.marcelocosta.cursomc.repositories.ItemPedidoRepository;
import com.marcelocosta.cursomc.repositories.PagamentoRepository;
import com.marcelocosta.cursomc.repositories.PedidoRepository;
import com.marcelocosta.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
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
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateDatabase() throws ParseException {
		
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama messa e banho ");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoraçao");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		
		Produto p1 =  new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00 );
		Produto p3 = new Produto(null, "Mouse", 80.00); 
		Produto p4 = new Produto(null, "Mesa de escritorio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		
		//associar a categoria ao produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		
		//associar produto a categoria
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		
		//adicionamdo os iten da ctaegoria ao bd
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		//add os itens de produto ao bd
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
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
		
		
		Cliente cli1 = new Cliente(null, "Pedro Henrique", "pedrohbc96@gmail.com", "231233445", TipoCliente.PESSOAFISICA, pe.encode("123"));
		//adidionar telefone
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		
		Cliente cli2 = new Cliente(null, "Juliana Santos", "eujusantos82@gmail.com", "04643260440", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("24463323", "93834493"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Cliente cli3 = new Cliente(null, "Marcelo Costa", "marcelojbcosta@gmail.com", "07234529462", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli3.getTelefones().addAll(Arrays.asList("27366323", "93839993"));
		cli3.addPerfil(Perfil.ADMIN);
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38770121", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua adonias pereira", "144", "Apto 1101", "afogados", "38220834", cli2, c2);
		//Endereco e4 = new Endereco(null, "Rua Carlos santos", "223", "casa", "setuval", "45454677", cli3, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3, e2));
		//cli3.getEnderecos().addAll(Arrays.asList(e4, e2));
		
		
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Pedido ped1 = new Pedido(null, sdf.parse("20/08/2019 21:42"), cli1, e1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("28/10/2019 19:35"), cli1, e2);
		
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/10/2019 14:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 00.00, 1, 2000.00);
		
		ItemPedido ip2 = new ItemPedido(ped1, p3, 00.00, 2, 80.00);
		
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
	}

}
