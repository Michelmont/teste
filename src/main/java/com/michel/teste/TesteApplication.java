package com.michel.teste;

import com.michel.teste.domain.Transacao;
import com.michel.teste.infrastructure.JsonProcessor;
import com.michel.teste.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TesteApplication  implements CommandLineRunner {
	@Autowired
	private TransacaoRepository repository;
	private JsonProcessor jsonProcessor;
	public static void main(String[] args) {
		SpringApplication.run(TesteApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {

		if(!repository.findAll().iterator().hasNext()) {
			jsonProcessor = new JsonProcessor();
			repository.saveAll(jsonProcessor.readJsonFile("data/data_1.json"));
			repository.saveAll(jsonProcessor.readJsonFile("data/data_2.json"));
			repository.saveAll(jsonProcessor.readJsonFile("data/data_3.json"));
			repository.saveAll(jsonProcessor.readJsonFile("data/data_4.json"));
			System.out.println("dados carregados com sucesso");
		} else {
			System.out.println("Os dados ja foram cerregados");
		}
	}

}
