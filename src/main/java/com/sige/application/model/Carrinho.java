package com.sige.application.model;

import com.sige.application.enums.FormaPagamento;
import com.sige.application.enums.StatusCarrinho;
import com.sige.application.enums.StatusPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carrinho")
public class Carrinho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private List<ItemCarrinho> itemCarrinhos;

    @Column(name = "valor_total")
    private double valorTotal;

    @Column(name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cartao", referencedColumnName = "id")
    private Cartao cartao;

    @Column(name = "status_pagamento")
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusCarrinho statusCarrinho;

    public Carrinho(long id, Usuario usuario, List<ItemCarrinho> itemCarrinhos, double valorTotal, FormaPagamento formaPagamento, Cartao cartao, StatusPagamento statusPagamento, StatusCarrinho statusCarrinho) {
        this.id = id;
        this.usuario = usuario;
        this.itemCarrinhos = itemCarrinhos;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.cartao = cartao;
        this.statusPagamento = statusPagamento;
        this.statusCarrinho = statusCarrinho;
    }

    public Carrinho() {
    }

    public StatusCarrinho getStatusCarrinho() {
        return statusCarrinho;
    }

    public void setStatusCarrinho(StatusCarrinho statusCarrinho) {
        this.statusCarrinho = statusCarrinho;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemCarrinho> getItemCarrinhos() {
        return itemCarrinhos;
    }

    public void setItemCarrinhos(List<ItemCarrinho> itemCarrinhos) {
        this.itemCarrinhos = itemCarrinhos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carrinho carrinho = (Carrinho) o;

        return id == carrinho.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
