package com.sige.application.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_carrinho")
public class ItemCarrinho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JoinColumn(name = "id_ingresso", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ingresso ingresso;

    public ItemCarrinho(long id, Ingresso ingresso) {
        this.id = id;
        this.ingresso = ingresso;
    }

    public ItemCarrinho() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemCarrinho that = (ItemCarrinho) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
