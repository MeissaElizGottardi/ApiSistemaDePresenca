package com.sistemadepresensa.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadepresensa.sistema.dto.GerenciamentoDto;
import com.sistemadepresensa.sistema.service.GerenciamentoService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class GerenciamentoController {

    @Autowired
    private GerenciamentoService service;

    @PostMapping("/entrada")
    public GerenciamentoDto entrada() {
        return service.entrada();
    }

    @PostMapping("/saida")
    public GerenciamentoDto saida() {
        return service.saida();
    }

    @GetMapping("/status")
    public GerenciamentoDto status() {
        return service.status();
    }

    @PostMapping("/config/limite")
    public ResponseEntity<?> definirLimite(@RequestParam int valor) {
        try {
            service.definirLimite(valor);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reset")
    public void resetar() {
        service.reset();
    }
}
