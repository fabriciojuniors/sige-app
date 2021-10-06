package com.sige.application.enums;

public enum Nivel {
    A("Administrador"),
    C("Cliente"),
    O("Operador");

    private String descricao;

    private Nivel(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
