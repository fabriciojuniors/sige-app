package com.sige.application.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numero", nullable = false, length = 100)
    private String numero;

    @Column(name = "mes_vencimento", nullable = false)
    private int mesVencimento;

    @Column(name = "ano_vencimento", nullable = false)
    private int anoVencimento;

    @Column(name = "titular", nullable = false)
    private String titular;

    @Column(name = "cpf", nullable = false)
    private String CPF;

    public Cartao(Long id, String numero, int mesVencimento, int anoVencimento, String titular, String CPF) {
        this.id = id;
        this.numero = numero;
        this.mesVencimento = mesVencimento;
        this.anoVencimento = anoVencimento;
        this.titular = titular;
        this.CPF = CPF;
    }

    public Cartao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getMesVencimento() {
        return mesVencimento;
    }

    public void setMesVencimento(int mesVencimento) {
        this.mesVencimento = mesVencimento;
    }

    public int getAnoVencimento() {
        return anoVencimento;
    }

    public void setAnoVencimento(int anoVencimento) {
        this.anoVencimento = anoVencimento;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
