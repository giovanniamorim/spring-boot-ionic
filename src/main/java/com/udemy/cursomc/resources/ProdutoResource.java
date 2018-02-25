package com.udemy.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.cursomc.domain.Produto;
import com.udemy.cursomc.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id){
		Produto obj = produtoService.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	

}
