package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.BarbeiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Barbeiro extends JpaRepository<BarbeiroEntity, Integer> {

    BarbeiroEntity findBarbeiroEntityByIdBarbeiro(Integer idBarbeiro);

    List<BarbeiroEntity> findBarbeiroEntityByCidadeLike(String cidade);

    BarbeiroEntity findBarbeiroEntityByIdUsuario(Integer idUsuario);

    @Query(value = "select distinct cidade from barbeiro order by cidade asc", nativeQuery = true)
    List<String> findCidades();

    @Modifying
    @Transactional
    @Query(value = "update barbeiro set nomeBarbearia = :nomeBarbearia, cep = :cep, numero = :numero, telefone = :telefone, cidade = :cidade, rua = :rua, bairro = :bairro, uf = :uf where idBarbeiro = :idBarbeiro", nativeQuery = true)
    void updateBarbeiro(@Param("nomeBarbearia") String nomeBarbearia, @Param("cep") String cep, @Param("numero") Integer numero, @Param("telefone") String telefone, @Param("cidade") String cidade, @Param("rua") String rua, @Param("bairro") String bairro, @Param("uf") String uf, @Param("idBarbeiro") Integer idBarbeiro);

}
