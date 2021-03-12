package com.max.projeto.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max.projeto.domain.Pedido;
import com.max.projeto.service.PedidoService;

@RestController
@RequestMapping(value= "/pedidos")
public class PedidoResource {
	
	
	@Autowired
	private PedidoService service;
	
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id){
		Pedido obj = service.find(id);
			return ResponseEntity.ok().body(obj);

		
		
	
	}

}
