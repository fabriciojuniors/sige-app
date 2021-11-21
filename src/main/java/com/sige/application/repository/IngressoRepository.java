package com.sige.application.repository;

import com.sige.application.enums.StatusIngresso;
import com.sige.application.model.Evento;
import com.sige.application.model.Ingresso;
import com.sige.application.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    @Query("select i from Ingresso i where i.usuario = ?1 and i.statusIngresso = ?2")
    List<Ingresso> getByUsuario(Usuario usuario, StatusIngresso statusIngresso);

    List<Ingresso> getByEvento(Evento evento);

    @Query("select count(i) from Ingresso i where i.evento.data >= ?1")
    Integer ingressosVendidos(LocalDate hoje);

}
