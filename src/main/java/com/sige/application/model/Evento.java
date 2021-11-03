package com.sige.application.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 120, nullable = false)
    @NotNull(message = "O nome é obrigatório")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Column(name = "detalhes")
    private String detalhes;

    @Column(name = "data", nullable = false)
    @NotNull(message = "A data é obrigatória")
    @NotBlank(message = "A data é obrigatória")
    private LocalDate data;

    @Column(name = "hora", nullable = false)
    @NotNull(message = "A hora é obrigatória")
    @NotBlank(message = "A hora é obrigatória")
    private Time hora;

    @Column(name = "duracao", nullable = false)
    @NotNull(message = "A duração é obrigatória")
    @NotBlank(message = "A duração é obrigatória")
    private double duracao;

    @JoinColumn(name = "id_local", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotBlank(message = "O local é obrigatório")
    @NotNull(message = "O local é obrigatório")
    private Local local;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Parceiro> parceiros;

    @Column(name = "valor_ingresso", nullable = false)
    @Min(value = 1, message = "O valor minimo é R$1,00")
    private double valorIngresso;

    @Column(name = "imagem64", columnDefinition = "TEXT")
    private String imagem64;

    public Evento(Long id, String nome, String detalhes, LocalDate data, Time hora, double duracao, Local local, List<Parceiro> parceiros, double valorIngresso) {
        this.id = id;
        this.nome = nome;
        this.detalhes = detalhes;
        this.data = data;
        this.hora = hora;
        this.duracao = duracao;
        this.local = local;
        this.parceiros = parceiros;
        this.valorIngresso = valorIngresso;
    }

    public Evento() {
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

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public List<Parceiro> getParceiros() {
        return parceiros;
    }

    public void setParceiros(List<Parceiro> parceiros) {
        this.parceiros = parceiros;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evento evento = (Evento) o;

        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
