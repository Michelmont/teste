package com.michel.teste.repository;

import com.michel.teste.domain.Transacao;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public  interface TransacaoRepository extends CrudRepository<Transacao, Integer> {


}
