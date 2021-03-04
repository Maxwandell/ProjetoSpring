package com.max.projeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.max.projeto.domain.Categoria;
import com.max.projeto.domain.Cidade;
import com.max.projeto.domain.Cliente;
import com.max.projeto.domain.Endereço;
import com.max.projeto.domain.Estado;
import com.max.projeto.domain.Produto;
import com.max.projeto.domain.enums.TipoCliente;
import com.max.projeto.repositoryes.CategoriaRepository;
import com.max.projeto.repositoryes.CidadeRepository;
import com.max.projeto.repositoryes.ClienteRepository;
import com.max.projeto.repositoryes.EndereçoRepository;
import com.max.projeto.repositoryes.EstadoRepository;
import com.max.projeto.repositoryes.ProdutoRepository;

@SpringBootApplication
public class ProjetoSpringApplication implements CommandLineRunner{
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EndereçoRepository endereçoRepository;

	
	

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null,"computador", 2000.00);
		Produto p2 = new Produto(null,"impressora", 800.00);
		Produto p3 = new Produto(null,"mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));		
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
	

		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado e1 = new Estado(null,"Minas Gerais");
		Estado e2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null, "Urbelândia",e1);
		Cidade c2 = new Cidade(null, "São Paulo",e2);
		Cidade c3 = new Cidade(null, "Campinas",e2);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

		
		Cliente cli1 = new Cliente(null,"Maria Silva","Maria.silva@email.com","1111222233", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("1111-2222","3333-4444")); 
		
		Endereço end1 = new Endereço(null,"flores","300","apto 30","jardim","300114455", cli1,c1);
		Endereço end2 = new Endereço(null,"marca","201","est 30","centro","200114455", cli1,c2);
		
		cli1.getEndereco().addAll(Arrays.asList(end2,end1));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		endereçoRepository.saveAll(Arrays.asList(end1,end2));

		
	}

}
