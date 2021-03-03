package com.max.projeto.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max.projeto.domain.Categoria;
import com.max.projeto.service.CategoryService;

@RestController
@RequestMapping(value= "/categorias")
public class CategoriaResource {
	
	
	@Autowired
	private CategoryService service;
	
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id){
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}

}