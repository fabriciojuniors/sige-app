package com.sige.application.exception;

import com.sige.application.enums.ExceptionOperacao;

public class ExceptionSchema {

    public String campo;
    public String mensagem;
    public String detalhes;
    public ExceptionOperacao operacao;

    public ExceptionSchema(String campo, String mensagem, String detalhes, ExceptionOperacao operacao) {
        this.campo = campo;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
        this.operacao = operacao;
    }

    public ExceptionSchema() {
    }
}
