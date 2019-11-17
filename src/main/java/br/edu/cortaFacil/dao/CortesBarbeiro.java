package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.CortesBarbeiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CortesBarbeiro extends JpaRepository<CortesBarbeiroEntity, Integer> {

    List<CortesBarbeiroEntity> findAllByIdBarbearia(Integer idBarbeiro);

    CortesBarbeiroEntity findByIdCorte(Integer idCorte);

}
