package com.sige.application.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExceptionOperacao {
    C("Cadastro"),
    A("Atualização"),
    D("Exclusão"),
    L("Leitura");

    private String descricao;

    private ExceptionOperacao(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getOperacao(){
        return this.name();
    }
}
