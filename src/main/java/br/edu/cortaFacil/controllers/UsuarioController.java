package br.edu.cortaFacil.controllers;

import br.edu.cortaFacil.auxiliar.Error;
import br.edu.cortaFacil.auxiliar.Resposta;
import br.edu.cortaFacil.auxiliar.UsuarioIndisponivelException;
import br.edu.cortaFacil.auxiliar.Utils;
import br.edu.cortaFacil.dao.Usuario;
import br.edu.cortaFacil.entity.UsuarioEntity;
import br.edu.cortaFacil.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {

    /*
    * controller responsavel pelas acoes referentes ao usuários da aplicacao
    *
    * o usuário é a nossa chave para a aplicacao funcionar, qualquer cliente ou barbeiro está associado a um usuário
    *
    * possui os métodos:
    *
    * cadastar um novo usuário (também verifica a disponibilidade do nome de usuário)
    * autenticar - usado no login
    *
    * */

    @Autowired
    Usuario usuario;

    Utils utils = new Utils();

    @Autowired
    UsuarioService usuarioService;

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
            usuarioService.cadastraUsuario(usuarioEntity);
        }
        catch (UsuarioIndisponivelException usu){

            log.error("Usuário já cadastrado", usu);

            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem(usu.getMessage())
                            .build())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){

            log.error("Ocorreu um erro ao executar o insert!", e);

            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem("Usuário não cadastrado devido a um erro interno")
                            .build())
                    .build(), HttpStatus.BAD_REQUEST);
        }

        usuarioEntity.setSenha(null);

        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Usuário cadastrado com sucesso")
                .object(usuarioEntity)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/autenticar")
    ResponseEntity<Resposta> autentica(@RequestBody UsuarioEntity usuarioEntity){

        UsuarioEntity user = null;

        try {

            usuarioEntity.setSenha(utils.getB64(usuarioEntity.getSenha()));

            user = usuario.findByUsuarioAndSenhaAndAtivo(usuarioEntity.getUsuario(), usuarioEntity.getSenha(), 1);
        }
        catch (Exception e){

            log.error("Ocorreu um erro ao realizar busca!", e);

            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem("Ocorreu um erro ao realizar busca")
                            .build())
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Resposta.builder()
                .object(user)
                .build(), HttpStatus.OK);
    }


}
