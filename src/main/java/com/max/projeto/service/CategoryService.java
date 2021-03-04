package com.max.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.max.projeto.domain.Categoria;
import com.max.projeto.repositoryes.CategoriaRepository;
import com.max.projeto.service.exception.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
