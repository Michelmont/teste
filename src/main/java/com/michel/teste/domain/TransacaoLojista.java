package com.michel.teste.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransacaoLojista {
    @Getter(AccessLevel.PUBLIC)
    private double price;
    @Getter(AccessLevel.PUBLIC)
    private int quantity;
    @Getter(AccessLevel.PUBLIC)
    private double volume;

}


