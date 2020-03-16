package com.michel.teste.domain;

import com.michel.teste.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
public class VendaLojistaFactory {
    @Autowired
    private TransacaoService service;

    public Stream<VendaLojista> vender(String produto, int numLojistas) {
        Stream<Transacao> transacao = service.listarTransacaoPorProduto(produto);
        Stream<Transacao> transacaoP = service.listarTransacaoPorProduto(produto);
        int qtdEstoque = transacao.mapToInt(Transacao::getQuantity).sum();

        if(qtdEstoque % numLojistas !=0) {
            return null;
        }
        List<VendaLojista> vendasLojistas = new ArrayList<>();

        for(int i=0; i< numLojistas; i++) { vendasLojistas.add(new VendaLojista());}
        //int index = 0;
        AtomicInteger resto = new AtomicInteger();
        AtomicBoolean ultimoTemMais = new AtomicBoolean(false);

        transacaoP.forEach( t -> {
            int qtdLojistas =0;

            if(t.getQuantity() % numLojistas ==0) {
                for(int i =0; i < numLojistas; i++) {
                    TransacaoLojista trans = new TransacaoLojista();
                    trans.setQuantity(t.getQuantity() / numLojistas);
                    trans.setPrice(Double.valueOf(t.getPrice().replace("$","")));
                    trans.setVolume(trans.getPrice() * trans.getQuantity());
                    vendasLojistas.get(i).getTransacoesLojista().add(trans);
                }
            } else {
                resto.set(t.getQuantity() % numLojistas);
                if(!ultimoTemMais.get()) {
                    qtdLojistas = t.getQuantity() / numLojistas;

                    for (int i = 0; i < numLojistas - 1; i++) {
                        TransacaoLojista trans = new TransacaoLojista();

                        trans.setQuantity(qtdLojistas);
                        trans.setPrice(Double.valueOf(t.getPrice().replace("$","")));
                        trans.setVolume(trans.getPrice() * trans.getQuantity());
                        vendasLojistas.get(i).getTransacoesLojista().add(trans);


                    }

                    TransacaoLojista trans = new TransacaoLojista();

                    trans.setQuantity(qtdLojistas + resto.intValue());
                    trans.setPrice(Double.valueOf(t.getPrice().replace("$","")));
                    trans.setVolume(trans.getPrice() * trans.getQuantity());
                    vendasLojistas.get(numLojistas - 1).getTransacoesLojista().add(trans);

                    ultimoTemMais.set(true);

                } else {
                    qtdLojistas = (t.getQuantity() + resto.intValue()) / numLojistas;

                    for (int i = 0; i < numLojistas - 1; i++) {

                        TransacaoLojista trans = new TransacaoLojista();

                        trans.setQuantity(qtdLojistas);
                        trans.setPrice(Double.valueOf(t.getPrice().replace("$","")));
                        trans.setVolume(trans.getPrice() * trans.getQuantity());
                        vendasLojistas.get(i).getTransacoesLojista().add(trans);
                    }

                    TransacaoLojista trans = new TransacaoLojista();

                    trans.setQuantity(qtdLojistas - resto.intValue());
                    trans.setPrice(Double.valueOf(t.getPrice().replace("$","")));
                    trans.setVolume(trans.getPrice() * trans.getQuantity());
                    vendasLojistas.get(numLojistas - 1).getTransacoesLojista().add(trans);

                    ultimoTemMais.set(false);
                }
            }


        });

        vendasLojistas.stream().forEach(v -> {

            v.setQtde(v.getTransacoesLojista().stream().mapToInt(TransacaoLojista::getQuantity).sum());

            v.setPrecoMedio(v.getTransacoesLojista().stream()
                    .mapToDouble(TransacaoLojista::getVolume).sum() / v.getQtde());



            v.setFinanceiro(v.getTransacoesLojista().stream().mapToDouble(TransacaoLojista::getVolume).sum());
        });

        return vendasLojistas.stream();

    }
}