package com.sige.application.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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

    public String getDescricao() {
        return descricao;
    }

    public String getSigla(){
        return this.name();
    }
}
