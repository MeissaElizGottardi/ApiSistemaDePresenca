package com.sistemadepresensa.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Gerenciamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int pessoas;
    private int limite;

    public Gerenciamento() {
        this.pessoas = 0;
        this.limite = 5;
    }

    public Long getId() {
        return id;
    }

    public int getPessoas() {
        return pessoas;
    }

    public void setPessoas(int pessoas) {
        this.pessoas = Math.max(0, pessoas);
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public boolean isPermitido() {
        return pessoas < limite;
    }

    public String getStatus() {
        return isPermitido() ? "Disponível" : "Lotado";
    }

    // 🔹 Regras de negócio
    public void entrar() {
        if (pessoas < limite) {
            pessoas++;
        }
    }

    public void sair() {
        if (pessoas > 0) {
            pessoas--;
        }
    }
}