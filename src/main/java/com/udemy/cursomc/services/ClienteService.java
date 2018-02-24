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
	
	public Cliente buscar(Integer id) {
		Cliente obj = categoriaRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Não foi encontrada nenhuma categoria com o nome: " + id + " Tipo: " + Cliente.class.getName());
		}
		
		return obj;
	}
}
