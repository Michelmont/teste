package com.michel.teste.domain;

import com.michel.teste.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
public class VendaLojistaFactory {
    @Autowired
    private TransacaoService service;

    public Stream<VendaLojista> vender(String produto, int numLojistas) {
        Stream<Transacao> transacao = service.listarTransacaoPorProduto(produto);
        AtomicInteger qtdEstoque  = new AtomicInteger();
        transacao.forEach(t -> qtdEstoque.set(qtdEstoque.intValue() + t.getQuantity()));

        if(qtdEstoque.intValue() % numLojistas !=0) {
            return null;
        }

        transacao.forEach( t -> {

        });

    }
}