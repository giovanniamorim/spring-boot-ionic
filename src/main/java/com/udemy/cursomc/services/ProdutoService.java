package com.udemy.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Produto;
import com.udemy.cursomc.repositories.ProdutoRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto find(Integer id) {
		Produto cliente = produtoRepository.findOne(id);
		if(cliente == null) {
			throw new ObjectNotFoundException("Não foi encontrada nenhum produto com o id: " + id);
		}
		
		return cliente;
	}
}
