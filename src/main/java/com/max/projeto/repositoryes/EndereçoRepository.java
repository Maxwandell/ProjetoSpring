package com.max.projeto.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.max.projeto.domain.Endereço;

@Repository
public interface EndereçoRepository extends JpaRepository<Endereço, Integer>{

}
