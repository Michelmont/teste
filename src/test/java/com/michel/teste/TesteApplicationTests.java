package com.michel.teste;

import com.michel.teste.domain.Transacao;
import com.michel.teste.infrastructure.JsonProcessor;
import com.michel.teste.repository.TransacaoRepository;
import com.michel.teste.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class TesteApplicationTests {
	@Autowired
	private TransacaoRepository repository;
	@Autowired
	private TransacaoService service;
	@Test
	void contextLoads() {
	}

	@Test
	void testReadValidJsonFile(){
		String file = "data/data_3.json";
		JsonProcessor jProcessor = new JsonProcessor();
		try {
			List<Transacao> trans =jProcessor.readJsonFile(file);
			repository.saveAll(trans);
			System.out.println( repository.count()	);}
		catch(Exception ex ) {
			System.out.println(ex);
		}


	}

	@Test
	void testViewDataFromBD(){
		try {
			List<Transacao> trans = (List<Transacao>) repository.findAll();
			trans.stream().filter(t -> t.getProduct().equals("EMMS")).forEach(t1 -> System.out.println(t1.toString()));
		} catch(Exception ex) {

		}
	}

	@Test
	void testFiltroPorProduto() {
		try {
				Stream<Transacao> transacoes = service.listarTransacaoPorProduto("EMMS");
				transacoes.forEach(t -> System.out.println(t.toString()));
		} catch(Exception ex) {
				System.out.println(ex);
		}
	}

	@Test
	void testDeleteMethod() {
		try {
			repository.deleteAll();
		}catch(Exception e) {

		}
	}
}
