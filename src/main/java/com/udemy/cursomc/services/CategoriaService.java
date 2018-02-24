package com.udemy.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.repositories.CategoriaRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Categoria obj = categoriaRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Não foi encontrada nenhuma categoria com o nome: " + id + " Tipo: " + Categoria.class.getName());
		}
		
		return obj;
	}
}
