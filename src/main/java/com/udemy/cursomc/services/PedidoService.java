package com.udemy.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Pedido;
import com.udemy.cursomc.repositories.PedidoRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository categoriaRepository;
	
	public Pedido buscar(Integer id) {
		Pedido obj = categoriaRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Não foi encontrada nenhuma categoria com o nome: " + id + " Tipo: " + Pedido.class.getName());
		}
		
		return obj;
	}
}
