package com.udemy.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.cursomc.domain.Endereco;
import com.udemy.cursomc.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id){
		Endereco obj = enderecoService.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
	

}
