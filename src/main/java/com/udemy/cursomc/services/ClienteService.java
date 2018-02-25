package com.udemy.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.repositories.ClienteRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository categoriaRepository;
	
	public Cliente find(Integer id) {
		Cliente cliente = categoriaRepository.findOne(id);
		if(cliente == null) {
			throw new ObjectNotFoundException("Não foi encontrada nenhum cliente com o id: " + id);
		}
		
		return cliente;
	}
}
