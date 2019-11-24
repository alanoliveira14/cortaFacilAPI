package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cliente extends JpaRepository<ClienteEntity, Integer> {

    ClienteEntity findByIdCliente(Integer idCliente);

    ClienteEntity findByIdUsuario(Integer idUsuario);

}
