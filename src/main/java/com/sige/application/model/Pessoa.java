package com.sige.application.model;

import com.sige.application.enums.Sexo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@MappedSuperclass
public class Pessoa {

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    @NotNull(message = "CPF não pode ser nulo")
    @NotBlank(message = "CPF não pode ser nulo")
    private String cpf;

    @Column(name = "nome", nullable = false, length = 120)
    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "Nome não pode ser nulo")
    private String nome;

    @Column(name = "sexo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "dt_nascimento", nullable = false)
    @NotNull(message = "Data de Nascimento não pode ser nula")
    private LocalDate nascimento;

    @Column(name = "telefone")
    @NotNull(message = "Telefone não pode ser nula")
    @NotBlank(message = "Telefone não pode ser nula")
    private String telefone;

    @JoinColumn(name = "id_endereco", referencedColumnName = "id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Endereco endereco;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Cartao> cartoes;

    public Pessoa(String cpf, String nome, Sexo sexo, LocalDate nascimento,
                  String telefone, Endereco endereco, List<Cartao> cartoes) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cartoes = cartoes;
    }

    public Pessoa() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public String getNascimentoFormatado(){
        return this.nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

}
