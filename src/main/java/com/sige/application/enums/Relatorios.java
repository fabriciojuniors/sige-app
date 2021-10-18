package com.sige.application.enums;

public enum Relatorios {
    LOCAIS("/Locais.jrxml", "Locais.pdf");

    private String arquivo;
    private String pdf;

    private Relatorios(String arquivo, String pdf) {
        this.arquivo = arquivo;
        this.pdf = pdf;
    }

    public String getArquivo() {
        return arquivo;
    }

    public String getPdf() {
        return pdf;
    }
}
