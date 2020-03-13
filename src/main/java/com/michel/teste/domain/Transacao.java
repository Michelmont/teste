package com.michel.teste.domain;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@ToString
@Data
@Getter
@Entity
public class Transacao {
    @Id
    @GeneratedValue
    private int id;
    @Getter(AccessLevel.PUBLIC)
    private String product;
    @Getter(AccessLevel.PUBLIC)
    private int quantity;
    @Getter(AccessLevel.PUBLIC)
    private String price;
    @Getter(AccessLevel.PUBLIC)
    private String type;
    @Getter(AccessLevel.PUBLIC)
    private String industry;
    @Getter(AccessLevel.PUBLIC)
    private String origin;
}