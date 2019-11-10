package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuario extends JpaRepository<UsuarioEntity,Integer> {

    UsuarioEntity findByUsuarioAndSenhaAndAtivo(String usuario, String senha, Integer ativo);
    UsuarioEntity findByIdUsuario(Integer id);
    Integer countUsuarioEntityByUsuario(String usuario);

}
