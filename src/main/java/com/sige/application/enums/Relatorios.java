package com.sige.application.enums;

public enum Relatorios {
    LOCAIS("/Locais.jrxml");

    private String arquivo;

    Relatorios(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getArquivo() {
        return arquivo;
    }
}
