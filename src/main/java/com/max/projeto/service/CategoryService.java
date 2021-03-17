package com.max.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.max.projeto.domain.Categoria;
import com.max.projeto.domain.Cliente;
import com.max.projeto.dto.CategoriaDTO;
import com.max.projeto.repositoryes.CategoriaRepository;
import com.max.projeto.service.exception.DataIntegryti;
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

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return obj = repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegryti("Não é possivel excluir.....");

		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();

	}
	public Page<Categoria> findPage(Integer page, Integer LinePage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, LinePage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getName());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setName(obj.getName());
	
	}

}
