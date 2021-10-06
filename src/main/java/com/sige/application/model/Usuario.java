package com.sige.application.model;

import com.sige.application.enums.Nivel;
import com.sige.application.enums.Sexo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nivel", nullable = false)
    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @Column(name = "email", nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "senha", nullable = false, length = 120)
    private String senha;

    public Usuario(String cpf, String nome, Sexo sexo, LocalDate nascimento,
                   String telefone, Endereco endereco, List<Cartao> cartoes,
                   Long id, Nivel nivel, String email, String senha) {
        super(cpf, nome, sexo, nascimento, telefone, endereco, cartoes);
        this.id = id;
        this.nivel = nivel;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
