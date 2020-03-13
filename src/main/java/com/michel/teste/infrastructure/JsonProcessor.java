package com.michel.teste.infrastructure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.michel.teste.domain.Transacao;
import com.michel.teste.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonProcessor {

    private TransacaoRepository transacaoRepository;

    @Autowired
    public JsonProcessor(TransacaoRepository transacaoRepository){
        this.transacaoRepository = transacaoRepository;
    }

    public JsonProcessor(){}

    public List<Transacao> readJsonFile(String fileName) throws IOException {
        ClassLoader classLoader = JsonProcessor.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // Create a JsonParser instance
        try (JsonParser jsonParser = mapper.getFactory().createParser(is)) {
            List<Transacao> transacoes = new ArrayList<Transacao>();
            // Check the first token
            if (jsonParser.nextToken() != JsonToken.START_OBJECT) {
                throw new IllegalStateException("Expected content to be an array");
            }

            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                // Read a contact instance using ObjectMapper and do something with it
                if(jsonParser.nextToken() != JsonToken.START_ARRAY) {
                    Transacao trans = mapper.readValue(jsonParser, Transacao.class);
                    transacoes.add(trans);
                }


            }
            //transacoes.stream().distinct().forEach( t -> System.out.println(t.toString()));
            return transacoes;


        }catch(Exception ex) {
            return null;
        }

    }
}
