package br.edu.cortaFacil.controllers;

import br.edu.cortaFacil.aux.Error;
import br.edu.cortaFacil.aux.Resposta;
import br.edu.cortaFacil.dao.Barbeiro;
import br.edu.cortaFacil.entity.BarbeiroEntity;
import br.edu.cortaFacil.service.BarbeariaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiro")
@Slf4j
public class BarbeariaController {

    @Autowired
    Barbeiro barbeiroDAO;

    @Autowired
    BarbeariaService barbeariaService;

    @GetMapping("/teste")
    ResponseEntity<BarbeiroEntity> barbeiroEntityResponseEntity(){

        return new ResponseEntity<>(new BarbeiroEntity(), HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    ResponseEntity<Resposta> newBarbeiro(@RequestBody BarbeiroEntity barbeiroEntity){

        try{

            barbeariaService.cadastrar(barbeiroEntity);


        }catch (Exception e){

            log.error("Erro ao realiar cadastro!", e);

            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem("Erro ao realizar cadastro!")
                            .build())
                    .build(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Cadastro realizado com sucesso!")
                .build(), HttpStatus.OK);

    }

    @GetMapping("/busca/barbearia")
    ResponseEntity<Resposta> buscaBarbearia(@RequestHeader(value = "token") String token){

        BarbeiroEntity barbeiro = barbeiroDAO.getBarbeiroEntityByIdBarbeiro(1);

        if(barbeiro == null || barbeiro.getIdBarbeiro() == null){
            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem("Erro interno")
                            .build())
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Resposta.builder()
                .object(barbeiro)
                .build(), HttpStatus.OK);

    }

    @GetMapping("/busca/barbearia/cidade")
    ResponseEntity<Resposta> buscaBarbeariaPorCidade(@RequestParam String cidade){

        List<BarbeiroEntity> barbeiroEntityByCidadeLike = barbeiroDAO.findBarbeiroEntityByCidadeLike(cidade);

        if(barbeiroEntityByCidadeLike == null || barbeiroEntityByCidadeLike.isEmpty()){

            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem("Erro interno")
                            .build())
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Resposta.builder()
                .object(barbeiroEntityByCidadeLike)
                .build(), HttpStatus.OK);

    }

}
