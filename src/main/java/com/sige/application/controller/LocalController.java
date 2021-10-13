package com.sige.application.controller;

import com.sige.application.model.Endereco;
import com.sige.application.model.Local;
import com.sige.application.repository.EnderecoRepository;
import com.sige.application.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocalController {

    @Autowired
    LocalRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Local save(Local local){
        Endereco e = local.getEndereco();
        e = enderecoRepository.save(e);
        local.setEndereco(e);
        return repository.save(local);
    }

    public Page<Local> getAll(int pagina){
        Pageable pageable = PageRequest.of(pagina-1, 5);
        return repository.findAll(pageable);
    }

}
