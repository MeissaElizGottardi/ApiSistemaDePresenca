package com.sistemadepresensa.sistema.dto;

public class GerenciamentoDto {

    private int pessoas;
    private int limite;
    private boolean permitido;
    private String status;

    // Construtor vazio
    public GerenciamentoDto() {}

    // Construtor completo
    public GerenciamentoDto(int pessoas, int limite, boolean permitido, String status) {
        this.pessoas = pessoas;
        this.limite = limite;
        this.permitido = permitido;
        this.status = status;
    }

    // Getters e Setters
    public int getPessoas() {
        return pessoas;
    }

    public void setPessoas(int pessoas) {
        this.pessoas = pessoas;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}