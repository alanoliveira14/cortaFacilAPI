package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.CortesBarbeiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CortesBarbeiro extends JpaRepository<CortesBarbeiroEntity, Integer> {
}
