package com.max.projeto;

import java.text.SimpleDateFormat;
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
import com.max.projeto.domain.ItemPedido;
import com.max.projeto.domain.Pagamento;
import com.max.projeto.domain.PagamentoComBoleto;
import com.max.projeto.domain.PagamentoComCartao;
import com.max.projeto.domain.Pedido;
import com.max.projeto.domain.Produto;
import com.max.projeto.domain.enums.EstadoPagamento;
import com.max.projeto.domain.enums.TipoCliente;
import com.max.projeto.repositoryes.CategoriaRepository;
import com.max.projeto.repositoryes.CidadeRepository;
import com.max.projeto.repositoryes.ClienteRepository;
import com.max.projeto.repositoryes.EndereçoRepository;
import com.max.projeto.repositoryes.EstadoRepository;
import com.max.projeto.repositoryes.ItemPedidoRepository;
import com.max.projeto.repositoryes.PagamentoRepository;
import com.max.projeto.repositoryes.PedidoRepository;
import com.max.projeto.repositoryes.ProdutoRepository;

@SpringBootApplication
public class ProjetoSpringApplication implements CommandLineRunner {
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Urbelândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria.silva@email.com", "1111222233",
				TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("1111-2222", "3333-4444"));

		Endereço end1 = new Endereço(null, "flores", "300", "apto 30", "jardim", "300114455", cli1, c1);
		Endereço end2 = new Endereço(null, "marca", "201", "est 30", "centro", "200114455", cli1, c2);

		cli1.getEndereco().addAll(Arrays.asList(end2, end1));

		clienteRepository.saveAll(Arrays.asList(cli1));
		endereçoRepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

		cli1.getPedido().addAll(Arrays.asList(ped1, ped2));

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00, 1, 200.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

		
		
		
		

	}

}
