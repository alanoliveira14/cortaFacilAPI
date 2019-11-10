package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.BarbeiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Barbeiro extends JpaRepository<BarbeiroEntity, Integer> {

    BarbeiroEntity findBarbeiroEntityByIdBarbeiro(Integer idBarbeiro);

    List<BarbeiroEntity> findBarbeiroEntityByCidadeLike(String cidade);

    BarbeiroEntity findBarbeiroEntityByIdUsuario(Integer idUsuario);
}
