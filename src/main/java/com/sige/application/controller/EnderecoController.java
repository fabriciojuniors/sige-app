package com.sige.application.controller;

import com.sige.application.model.Endereco;
import com.sige.application.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoController {

    @Autowired
    EnderecoRepository repository;

    public Endereco save(Endereco e){
        return repository.save(e);
    }

}
