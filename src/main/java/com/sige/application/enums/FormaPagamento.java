package com.sige.application.enums;

public enum FormaPagamento {
    PIX("Pix"),
    CARTAO("Cartao"),
    GRATIS("Grátis");

    private String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
