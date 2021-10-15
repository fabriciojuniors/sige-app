package com.sige.application.model;

import com.sige.application.enums.UF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rua", nullable = false)
    @NotNull(message = "A rua não pode ser nula")
    @NotBlank(message = "A rua não pode ser nula")
    private String rua;

    @Column(name = "numero", nullable = false)
    private int numero;

    @Column(name = "uf", length = 2)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "A UF não pode ser nula")
    private UF uf;

    @Column(name = "cidade", nullable = false)
    @NotNull(message = "A cidade não pode ser nula")
    @NotBlank(message = "A cidade não pode ser nula")
    private String cidade;

    @Column(name = "complemento", nullable = true)
    private String complemento;

    @Column(name = "cep", nullable = false, length = 9)
    @NotNull(message = "O CEP não pode ser nulo")
    @NotBlank(message = "O CEP não pode ser nulo")
    @Size(max = 8, min = 8, message = "O tamanho do CEP é 8 digitos.")
    private String cep;

    public Endereco(Long id, String rua, int numero, UF uf, String cidade, String complemento, String cep) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.uf = uf;
        this.cidade = cidade;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
