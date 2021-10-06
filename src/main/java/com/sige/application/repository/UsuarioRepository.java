package com.sige.application.repository;

import com.sige.application.enums.Nivel;
import com.sige.application.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByEmail(String email);
    List<Usuario> findByCpf(String cpf);
    Page<Usuario> findByNivel(Nivel nivel, Pageable pageable);

    @Query("select u from Usuario u where u.email = :email and u.senha = :senha")
    Usuario autenticar(String email, String senha);

}
