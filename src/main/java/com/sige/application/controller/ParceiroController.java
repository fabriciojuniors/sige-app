package com.sige.application.controller;

import com.sige.application.enums.ExceptionOperacao;
import com.sige.application.exception.CampoException;
import com.sige.application.model.Endereco;
import com.sige.application.model.Local;
import com.sige.application.model.Parceiro;
import com.sige.application.model.Usuario;
import com.sige.application.repository.EnderecoRepository;
import com.sige.application.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParceiroController {

    @Autowired
    ParceiroRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Parceiro save(Parceiro parceiro){

        List<Parceiro> validaCPF = repository.findByCpf(parceiro.getCpf());

        if(!validaCPF.isEmpty()){
            for(Parceiro parceiro1 : validaCPF){
                if(!parceiro.equals(parceiro1)){
                    throw new CampoException("parceiro.cpf", "CPF informado já cadastrado.", "", ExceptionOperacao.C);
                }
            }
        }

        Endereco endereco = enderecoRepository.save(parceiro.getEndereco());
        parceiro.setEndereco(endereco);

        return repository.save(parceiro);
    }

    public Page<Parceiro> getAll(int pagina){
        Pageable pageable = PageRequest.of(pagina-1, 5);
        return repository.findAll(pageable);
    }

    public void delete(int id){
        Parceiro parceiro = repository.findById((long) id).get();
        if(parceiro == null){
            throw new CampoException("parceiro.id", "Não foi possível excluir o parceiro.", "Não encontrado parceiro com ID " + id + " para exclusão.", ExceptionOperacao.D);
        }else{
            repository.delete(parceiro);
        }
    }

}
