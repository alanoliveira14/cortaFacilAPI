package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface Cliente extends JpaRepository<ClienteEntity, Integer> {

    ClienteEntity findByIdCliente(Integer idCliente);

    ClienteEntity findByIdUsuario(Integer idUsuario);

    @Modifying
    @Transactional
    @Query(value = "update cliente set nomeCompleto = :nome, telefone = :telefone, email = :email where idUsuario = :idUsuario", nativeQuery = true)
    void updateCliente(@Param("nome") String nome, @Param("telefone") String telefone, @Param("email") String email, @Param("idUsuario") Integer idUsuario);

}
