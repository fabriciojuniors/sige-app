package com.sige.application.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ingresso")
public class Ingresso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Evento evento;

    @Column(name = "cpf", nullable = false)
    @CPF
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    public Ingresso(Long id, Evento evento, String cpf, String nome, Usuario usuario) {
        this.id = id;
        this.evento = evento;
        this.cpf = cpf;
        this.nome = nome;
        this.usuario = usuario;
    }

    public Ingresso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingresso ingresso = (Ingresso) o;

        return Objects.equals(id, ingresso.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
