package com.sige.application.repository;

import com.sige.application.model.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {

    List<Parceiro> findByCpf(String cpf);
}
