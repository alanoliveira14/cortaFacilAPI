package br.edu.cortaFacil.controllers;

import br.edu.cortaFacil.aux.Resposta;
import br.edu.cortaFacil.aux.Utils;
import br.edu.cortaFacil.dao.Usuario;
import br.edu.cortaFacil.entity.UsuarioEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {


    @Autowired
    Usuario usuario;

    Utils utils = new Utils();

    @GetMapping("/")
    ResponseEntity<String> teste(){

        usuario.save(UsuarioEntity.builder()
                .ativo(1)
                .nvlLogin(1)
                .senha("123456")
                .usuario("alan")
                .build());

        return new ResponseEntity<>("deu certo coroio", HttpStatus.OK);
    }


    @PostMapping("/cadastrar")
    ResponseEntity<Resposta> cadastra(@RequestBody UsuarioEntity usuarioEntity){

        try {

            usuarioEntity.setSenha(utils.getB64(usuarioEntity.getSenha()));

            usuarioEntity.setAtivo(1);

            usuario.save(usuarioEntity);
        }
        catch (Exception e){

            log.error("Ocorreu um erro ao executar o insert!", e);

            return new ResponseEntity<>(Resposta.builder()
                    .mensagem("Usuário não cadastrado devido ao um erro interno")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Usuário inserido com sucesso")
                .build(), HttpStatus.OK);
    }

    @PostMapping("/autenticar")
    ResponseEntity<Resposta> autentica(@RequestBody UsuarioEntity usuarioEntity){

        UsuarioEntity user = null;

        try {

            usuarioEntity.setSenha(utils.getB64(usuarioEntity.getSenha()));

            user = usuario.findByUsuarioAndSenhaAndAtivo(usuarioEntity.getUsuario(), usuarioEntity.getSenha(), 1);
            //user = usuario.findByIdUsuario(1);
        }
        catch (Exception e){

            log.error("Ocorreu um erro ao realizar busca!", e);

            return new ResponseEntity<>(Resposta.builder()
                    .mensagem("Ocorreu um erro ao realizar busca")
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Resposta.builder()
                .object(user)
                .build(), HttpStatus.OK);
    }


}