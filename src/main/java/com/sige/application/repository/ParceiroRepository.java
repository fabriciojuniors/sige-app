package com.sige.application.repository;

import com.sige.application.model.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
}
