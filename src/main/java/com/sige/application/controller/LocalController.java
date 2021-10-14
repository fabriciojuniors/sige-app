package com.sige.application.controller;

import com.sige.application.enums.ExceptionOperacao;
import com.sige.application.exception.CampoException;
import com.sige.application.model.Endereco;
import com.sige.application.model.Local;
import com.sige.application.repository.EnderecoRepository;
import com.sige.application.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

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

    public void delete(int id){
        Local local = repository.findById((long) id).get();
        if(local == null){
            throw new CampoException("local.id", "Não foi possível excluir o local.", "Não encontrado local com ID " + id + " para exclusão.", ExceptionOperacao.D);
        }else{
            repository.delete(local);
        }
    }

}
