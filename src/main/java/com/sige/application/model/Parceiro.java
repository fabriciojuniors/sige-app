package com.sige.application.model;

import com.sige.application.enums.Sexo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "parceiro")
public class Parceiro extends Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Parceiro(String cpf, String nome, Sexo sexo, LocalDate nascimento, String telefone, Endereco endereco, List<Cartao> cartoes, Long id) {
        super(cpf, nome, sexo, nascimento, telefone, endereco, cartoes);
        this.id = id;
    }

    public Parceiro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parceiro parceiro = (Parceiro) o;

        return id != null ? id.equals(parceiro.id) : parceiro.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
