package com.sige.application.enums;

public enum UF {
    SC("Santa Catarina"),
    RS("Rio Grande do Sul"),
    PR("Paraná");

    private String descricao;

    private UF(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
