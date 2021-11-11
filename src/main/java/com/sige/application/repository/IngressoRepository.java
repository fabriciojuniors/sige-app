package com.sige.application.repository;

import com.sige.application.model.Ingresso;
import com.sige.application.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    List<Ingresso> getByUsuario(Usuario usuario);

}
