package com.sige.application.enums;

public enum Sexo {
    F("Feminino"),
    M("Masculino");

    private String descricao;

    private Sexo(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
