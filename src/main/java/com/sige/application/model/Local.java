package com.sige.application.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "local")
public class Local implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull(message = "O ID não pode ser nulo")
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @Column(name = "informacoes_adicionais", nullable = true, length = 255)
    private String informacoesAdicionais;

    @JoinColumn(name = "id_endereco", referencedColumnName = "id", nullable = true)
    @OneToOne(fetch = FetchType.EAGER)
    @Valid
    private Endereco endereco;

    @Column(name = "capacidade", nullable = false)
    @Min(value = 1, message = "A capacidade não pode ser inferior a 1")
    private int capacidade;

    @Column(name = "telefone", nullable = false)
    @NotNull(message = "O telefone não pode ser nulo")
    @NotBlank(message = "O telefone não pode ser branco")
    private String telefone;

    public Local(Long id, String nome, String informacoesAdicionais, Endereco endereco, int capacidade, String telefone) {
        this.id = id;
        this.nome = nome;
        this.informacoesAdicionais = informacoesAdicionais;
        this.endereco = endereco;
        this.capacidade = capacidade;
        this.telefone = telefone;
    }

    public Local() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Local local = (Local) o;

        return id.equals(local.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
