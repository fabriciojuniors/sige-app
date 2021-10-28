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
import org.springframework.stereotype.Service;

@Service
public class EventoController {

    @Autowired
    EventoRepository repository;

    public Evento save(Evento evento){
        return repository.save(evento);
    }

    public Page<Evento> getAll(int pagina){
        Pageable pageable = PageRequest.of(pagina-1, 5);
        return repository.findAll(pageable);
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
