package com.sige.application.enums;

public enum StatusPagamento {
    A("Aprovado"),
    N("Negado"),
    P("Em processamento"),
    S("Sem dados de pagamento");

    private String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }
}
