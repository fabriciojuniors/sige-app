package com.sige.application.exception;

import com.sige.application.enums.ExceptionOperacao;

public class LoginException extends RuntimeException{
    private String campo;
    private String mensagem;
    private String detalhes;
    private ExceptionOperacao operacao;

    public LoginException() {
        this.campo = "";
        this.mensagem = "Usuário ou senha inválidos.";
        this.detalhes = "";
        this.operacao = ExceptionOperacao.L;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public ExceptionOperacao getOperacao() {
        return operacao;
    }

    public void setOperacao(ExceptionOperacao operacao) {
        this.operacao = operacao;
    }
}
