package com.max.projeto.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.max.projeto.domain.Categoria;
import com.max.projeto.dto.CategoriaDTO;
import com.max.projeto.service.CategoryService;

@RestController
@RequestMapping(value= "/categorias")
public class CategoriaResource {
	
	
	@Autowired
	private CategoryService service;
	
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id){
		Categoria obj = service.find(id);
			return ResponseEntity.ok().body(obj);

		
	}
	

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
	@PutMapping(value= "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO,@PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		 return ResponseEntity.noContent().build();

	
	}
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(  ){
		List <Categoria> list = service.findAll();
		List<CategoriaDTO> listaDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listaDTO);
	}
	@GetMapping(value= "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0")Integer page,
			@RequestParam(value = "LinePage",defaultValue = "24")Integer LinePage, 
			@RequestParam(value = "orderBy",defaultValue = "name")	String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC")String direction  ){
		Page <Categoria> list = service.findPage(page,LinePage,orderBy,direction);
		Page<CategoriaDTO> listaDTO = list.map(obj -> new CategoriaDTO(obj));
			return ResponseEntity.ok().body(listaDTO);
	}
}
