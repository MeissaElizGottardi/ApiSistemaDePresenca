package com.sistemadepresensa.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadepresensa.sistema.dto.GerenciamentoDto;
import com.sistemadepresensa.sistema.model.Gerenciamento;
import com.sistemadepresensa.sistema.repository.GerenciamentoRepository;

@Service
public class GerenciamentoService {

    @Autowired
    private GerenciamentoRepository repository;

    // Sempre pega ou cria o único registro
    public Gerenciamento getGerenciamento() {
        return repository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> repository.save(new Gerenciamento()));
    }

    // Entrada
    public GerenciamentoDto entrada() {
        Gerenciamento g = getGerenciamento();

        g.entrar();

        repository.save(g);

        return toDto(g);
    }

    // Saída
    public GerenciamentoDto saida() {
        Gerenciamento g = getGerenciamento();

        g.sair();

        repository.save(g);

        return toDto(g);
    }

    // Status
    public GerenciamentoDto status() {
        Gerenciamento g = getGerenciamento();
        return toDto(g);
    }

    // Alterar limite
    public void definirLimite(int novoLimite) {
        Gerenciamento g = getGerenciamento();

        g.setLimite(novoLimite);

        repository.save(g);
    }

    // Reset
    public void reset() {
        Gerenciamento g = getGerenciamento();

        g.setPessoas(0);

        repository.save(g);
    }

    // Converter Model em DTO
    private GerenciamentoDto toDto(Gerenciamento g) {
        return new GerenciamentoDto(
                g.getPessoas(),
                g.getLimite(),
                g.isPermitido(),
                g.getStatus()
        );
    }
}
