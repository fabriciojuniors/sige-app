package com.sige.application.enums;

public enum ClassificacaoIndicativa {
    L("Livre", 0),
    DEZ("Acima de 10 anos de idade", 10),
    DOZE("Acima de 12 anos de idade", 12),
    QUATORZE("Acima de 14 anos de idade", 14),
    DEZESSEIS("Acima de 16 anos de idade", 16),
    DEZOITO("Acima de 18 anos de idade", 18);

    private String descricao;
    private int idade;

    ClassificacaoIndicativa(String descricao, int idade) {
        this.descricao = descricao;
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdade() {
        return idade;
    }
}
