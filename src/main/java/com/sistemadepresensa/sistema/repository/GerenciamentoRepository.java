package com.sistemadepresensa.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemadepresensa.sistema.model.Gerenciamento;

@Repository
public interface GerenciamentoRepository extends JpaRepository<Gerenciamento, Long> {

}
