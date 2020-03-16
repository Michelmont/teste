package com.michel.teste.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class VendaLojista {

    private List<TransacaoLojista> transacoesLojista = new ArrayList<>();
    private int qtde;
    private double financeiro;
    private double precoMedio;

}
