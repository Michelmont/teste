package com.michel.teste.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class VendaLojista {

    private List<Integer> quantity;
    private List<Double> price;
    private List<Double> volume;
    private Integer totalQuantity;
    private Double financeiro;
    private Double precoMedio;
}
