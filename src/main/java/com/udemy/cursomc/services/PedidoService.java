package com.udemy.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Pedido;
import com.udemy.cursomc.repositories.PedidoRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido pedido = pedidoRepository.findOne(id);
		if(pedido == null) {
			throw new ObjectNotFoundException("Não foi encontrada nenhum pedido com o id: " + id);
		}
		
		return pedido;
	}
}
