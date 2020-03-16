package com.michel.teste.controller;
import com.michel.teste.domain.Transacao;
import com.michel.teste.domain.VendaLojista;
import com.michel.teste.domain.VendaLojistaFactory;
import com.michel.teste.repository.TransacaoRepository;
import com.michel.teste.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;


@RestController
public class TransacaoController {
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private TransacaoService service;
    @Autowired
    VendaLojistaFactory venda;
    @GetMapping("/transacoes")
    public List<Transacao> Listar(){
        List<Transacao> lista = (List<Transacao>) transacaoRepository.findAll();
        return lista;
    }
    @GetMapping("/transacoes/produto/{produto}")
    public Stream<Transacao> listarTransacaoPorProduto(@PathVariable String produto){
        return service.listarTransacaoPorProduto(produto);
    }

    @GetMapping("/transacoes/vender/{produto}/{lojistas}")
    public Stream<VendaLojista> vender(@PathVariable String produto, @PathVariable int lojistas){
        return venda.vender(produto, lojistas);
    }
}
