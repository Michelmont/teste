package com.michel.teste.service;

import com.michel.teste.domain.Transacao;
import com.michel.teste.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;
@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository repository;

    public Stream<Transacao> listarTransacaoPorProduto(String produto) {
        return ((List<Transacao>) repository.findAll()).stream().filter(t -> t.getProduct().equals(produto));
    }



}
