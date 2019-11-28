package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.CortesBarbeiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CortesBarbeiro extends JpaRepository<CortesBarbeiroEntity, Integer> {

    List<CortesBarbeiroEntity> findAllByIdBarbearia(Integer idBarbeiro);

    CortesBarbeiroEntity findByIdCorte(Integer idCorte);


    @Modifying
    @Transactional
    @Query(value = "update cortesBarbeiro set nomeCorte = :nomeCorte, preco = :preco, tempoMedio = :tempoMedio where idCorte = :idCorte", nativeQuery = true)
    void updateCorte(@Param("nomeCorte") String nomeCorte, @Param("preco") Double preco, @Param("tempoMedio") Integer tempoMedio, @Param("idCorte") Integer idCorte);

}
