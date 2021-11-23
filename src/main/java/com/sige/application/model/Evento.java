package com.sige.application.model;

import com.sige.application.enums.ClassificacaoIndicativa;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private LocalDate data;

    @Column(name = "hora", nullable = false)
    private Time hora;

    @Column(name = "duracao", nullable = false)
    @Min(value = 1, message = "A duração deve ser no minimo 1 minuto.")
    private double duracao;

    @JoinColumn(name = "id_local", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Local local;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private List<Parceiro> parceiros;

    @Column(name = "valor_ingresso", nullable = false)
    @Min(value = 0, message = "O valor minimo é R$0")
    private double valorIngresso;

    @Column(name = "classificacao", nullable = true)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "A classificação indicativa não pode ser nula")
    private ClassificacaoIndicativa classificacaoIndicativa;

    /*@Column(name = "imagem64", columnDefinition = "TEXT")
    private String imagem64;*/

    @Column(name = "gera_certificado")
    private boolean geraCertificado;

    public Evento(Long id, String nome, String detalhes, LocalDate data, Time hora, double duracao, Local local, List<Parceiro> parceiros, double valorIngresso, boolean geraCertificado, ClassificacaoIndicativa classificacaoIndicativa) {
        this.id = id;
        this.nome = nome;
        this.detalhes = detalhes;
        this.data = data;
        this.hora = hora;
        this.duracao = duracao;
        this.local = local;
        this.parceiros = parceiros;
        this.valorIngresso = valorIngresso;
        this.geraCertificado = geraCertificado;
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public Evento() {
    }

    public boolean isGeraCertificado() {
        return geraCertificado;
    }

    public void setGeraCertificado(boolean geraCertificado) {
        this.geraCertificado = geraCertificado;
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

    public ClassificacaoIndicativa getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public String getDataFormatada(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.data.format(formato);
    }

   /* public String getImagem64() {
        return imagem64;
    }

    public void setImagem64(String imagem64) {
        this.imagem64 = imagem64;
    }*/

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

