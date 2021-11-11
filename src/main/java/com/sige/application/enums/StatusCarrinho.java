package com.sige.application.enums;

public enum StatusCarrinho {
    A("Aberto"),
    F("Fechado");

    private String descricao;

    StatusCarrinho(String descricao) {
        this.descricao = descricao;
    }
}
