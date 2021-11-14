package com.sige.application.enums;

public enum StatusIngresso {
    EMITIDO("Emitido"),
    AUTORIZADO("Autorizado");

    private String descricao;

    StatusIngresso(String descricao) {
        this.descricao = descricao;
    }
}
