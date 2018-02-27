package com.udemy.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.ItemPedido;
import com.udemy.cursomc.domain.PagamentoComBoleto;
import com.udemy.cursomc.domain.Pedido;
import com.udemy.cursomc.domain.enums.EstadoPagamento;
import com.udemy.cursomc.repositories.ItemPedidoRepository;
import com.udemy.cursomc.repositories.PagamentoRepository;
import com.udemy.cursomc.repositories.PedidoRepository;
import com.udemy.cursomc.repositories.ProdutoRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido pedido = pedidoRepository.findOne(id);
		if(pedido == null) {
			throw new ObjectNotFoundException("Não foi encontrada nenhum pedido com o id: " + id);
		}
		
		return pedido;
	}

	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for (ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreço());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.save(pedido.getItens());
		
		return pedido;
	}
}








