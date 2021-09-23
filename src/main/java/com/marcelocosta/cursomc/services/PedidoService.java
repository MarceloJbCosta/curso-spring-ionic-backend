package com.marcelocosta.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelocosta.cursomc.domain.ItemPedido;
import com.marcelocosta.cursomc.domain.PagamentoComBoleto;
import com.marcelocosta.cursomc.domain.Pedido;
import com.marcelocosta.cursomc.domain.enums.EstadoPagamento;
import com.marcelocosta.cursomc.repositories.ItemPedidoRepository;
import com.marcelocosta.cursomc.repositories.PagamentoRepository;
import com.marcelocosta.cursomc.repositories.PedidoRepository;
import com.marcelocosta.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	//esta notacao faz a o estanciamento automatico
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	//operacao para buscar a categoria por codigo
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encpntrado! ID: " + id + ", Tipo " +
				Pedido.class.getName()));
		}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj); 
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
		
	}

}
