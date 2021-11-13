package com.sige.application.repository;

import com.sige.application.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    //lower(concat('%', :nameToFind,'%'))")
    @Query("select e from Evento e where lower(e.nome) like lower(concat('%', ?1, '%'))")
    Page<Evento> findAllByNome(String nome, Pageable page);

    @Query("select count(i) from Ingresso i where i.evento = ?1")
    long getIngressosVendidos(Evento evento);
}
