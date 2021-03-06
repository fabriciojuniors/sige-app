package com.sige.application.controller;

import com.sige.application.enums.ExceptionOperacao;
import com.sige.application.exception.CampoException;
import com.sige.application.model.Endereco;
import com.sige.application.model.Evento;
import com.sige.application.model.Local;
import com.sige.application.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventoController {

    @Autowired
    EventoRepository repository;

    public Evento save(Evento evento){
        Local local = evento.getLocal();

        if(local == null || local.getId().equals(null) || local.getId() == 0){
            throw new CampoException("evento.local", "Local é obrigatório", "Não informado local para o evento", ExceptionOperacao.C);
        }else{
            return repository.save(evento);
        }

    }

    public Page<Evento> getAll(int pagina){
        Pageable pageable = PageRequest.of(pagina-1, 5, Sort.by(Sort.Order.by("id")));
        return repository.findAll(pageable);
    }

    public Page<Evento> getAllMobile(int pagina){
        Pageable pageable = PageRequest.of(pagina-1, 10, Sort.by(Sort.Order.by("id")));
        return repository.findAllMobile(LocalDate.now(), pageable) ;
    }

    public Page<Evento> getAllMobile(int pagina, String filtro){
        Pageable pageable = PageRequest.of(pagina-1, 10, Sort.by(Sort.Order.by("id")));
        return repository.findAllByNome(filtro, pageable);
    }

    public void delete(int id){
        Evento evento = repository.findById((long) id).get();
        if(evento == null){
            throw new CampoException("evento.id", "Não foi possível excluir o evento.", "Não encontrado evento com ID " + id + " para exclusão.", ExceptionOperacao.D);
        }else{
            repository.delete(evento);
        }
    }
}
