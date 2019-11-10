package br.edu.cortaFacil.service;

import br.edu.cortaFacil.aux.UsuarioIndisponivelException;
import br.edu.cortaFacil.aux.Utils;
import br.edu.cortaFacil.dao.Usuario;
import br.edu.cortaFacil.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UsuarioService {

    public  UsuarioService(Usuario usuario){

    }

    Utils utils = new Utils();

    @Autowired
    Usuario usuarioDAO;

    public void cadastraUsuario(UsuarioEntity usuario) throws Exception {

        try {

            Integer disponibilidade = verificaDisponibilidade(usuario);

            if (disponibilidade >0){
                throw new UsuarioIndisponivelException("Usuário já cadastrado anteriormente");
            }

            usuario.setSenha(utils.getB64(usuario.getSenha()));
            geraToken(usuario);

            usuario.setAtivo(1);

            usuarioDAO.save(usuario);
        } catch (Exception e){
            throw e;
        }
    }


    public Integer verificaDisponibilidade(UsuarioEntity usuarioEntity){

        return usuarioDAO.countUsuarioEntityByUsuario(usuarioEntity.getUsuario());

    }

    public void geraToken(UsuarioEntity usuarioEntity){

        String millis = String.valueOf(System.currentTimeMillis());

        usuarioEntity.setToken(utils.getB64(usuarioEntity.getUsuario()+millis));

    }


}
