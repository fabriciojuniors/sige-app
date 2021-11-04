package com.sige.application.model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "parametros")
public class Parametros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "percentual_capacidade")
    @Min(value = 1, message = "O percentual minimo é 1%")
    @Max(value = 100, message = "O percentual não pode ser maior que 100%")
    private int percentualCapacidade;

    @Column(name = "qtd_pessoa")
    @Min(value = 1, message = "A quantidade minima não pode ser inferior a zero")
    private int qtdPessoa;

    public Parametros(long id, int percentualCapacidade, int qtdPessoa) {
        this.id = id;
        this.percentualCapacidade = percentualCapacidade;
        this.qtdPessoa = qtdPessoa;
    }

    public Parametros() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPercentualCapacidade() {
        return percentualCapacidade;
    }

    public void setPercentualCapacidade(int percentualCapacidade) {
        this.percentualCapacidade = percentualCapacidade;
    }

    public int getQtdPessoa() {
        return qtdPessoa;
    }

    public void setQtdPessoa(int qtdPessoa) {
        this.qtdPessoa = qtdPessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parametros that = (Parametros) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
