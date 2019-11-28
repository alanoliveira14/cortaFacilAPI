package br.edu.cortaFacil.controllers;

import br.edu.cortaFacil.aux.Error;
import br.edu.cortaFacil.aux.Resposta;
import br.edu.cortaFacil.dao.Barbeiro;
import br.edu.cortaFacil.dao.CortesBarbeiro;
import br.edu.cortaFacil.entity.BarbeiroEntity;
import br.edu.cortaFacil.entity.CortesBarbeiroEntity;
import br.edu.cortaFacil.service.BarbeariaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/barbeiro")
@Slf4j
public class BarbeariaController {

    /*
    *
    * controller de barbearia - responsável por realizar todas ações que sao relacionadas aos barbeiros
    *
    * possui os metodos:
    * criar um barbeiro
    * listar os barbeiros de uma cidade
    * listart as cidades em que temos um barbeiro cadastrado
    * buscar infos de uma determinada barbearia
    * cadastrar um corte para o barbeiro
    * listar os cortes de um barbeiro
    * buscar infos de um corte especifico
    * */

    @Autowired
    Barbeiro barbeiroDAO;

    @Autowired
    CortesBarbeiro cortesBarbeiroDAO;

    @Autowired
    BarbeariaService barbeariaService;

    @GetMapping("/lista/cidades")
    ResponseEntity<Resposta> barbeiroEntityResponseEntity(){

        List<String> distinctByCidade = barbeiroDAO.findCidades();

        return new ResponseEntity<>(Resposta.builder()
                .object(distinctByCidade)
                .build(), HttpStatus.OK);
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
    ResponseEntity<Resposta> buscaBarbearia(@RequestHeader(value = "barbearia") Integer id){

        BarbeiroEntity barbeiro = barbeiroDAO.findBarbeiroEntityByIdBarbeiro(id);

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

    @PostMapping("/corte/cadastrar")
    ResponseEntity<Resposta> cadastrarCorte(@RequestBody CortesBarbeiroEntity cortesBarbeiroEntity, @RequestHeader(value = "token") String token){

        try{

            barbeariaService.cadastraCorte(cortesBarbeiroEntity, token);

        }catch (Exception e){

            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem("Erro ao cadastrar corte!")
                            .build())
                    .build(), HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Corte cadastrado com sucesso!")
                .build(), HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/corte/listar")
    ResponseEntity<Resposta> listarCortes(@RequestParam Integer barbeiro, @RequestParam String tipo){

        if(!"cliente".equalsIgnoreCase(tipo)) {
            BarbeiroEntity barbeiroEntityByIdUsuario = barbeiroDAO.findBarbeiroEntityByIdUsuario(barbeiro);
            barbeiro = barbeiroEntityByIdUsuario.getIdBarbeiro();
        }
        List<CortesBarbeiroEntity> cortes = cortesBarbeiroDAO.findAllByIdBarbearia(barbeiro);

        if(cortes == null || cortes.isEmpty()){
            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem("Barbeiro não possui cortes")
                            .build())
                    .object(Collections.EMPTY_LIST)
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Resposta.builder()
                .object(cortes)
                .build(),
                HttpStatus.OK);

    }


    @GetMapping("/corte/encontra")
    ResponseEntity<Resposta> cortePorId(@RequestParam Integer idCorte){

        CortesBarbeiroEntity byIdCorte = cortesBarbeiroDAO.findByIdCorte(idCorte);

        return new ResponseEntity<>(Resposta.builder()
                .object(byIdCorte)
                .build(),
                HttpStatus.OK);

    }

    @PostMapping("/corte/atualiza")
    ResponseEntity<Resposta> atualizaCorte(@RequestBody CortesBarbeiroEntity cortesBarbeiroEntity){

        cortesBarbeiroDAO.updateCorte(cortesBarbeiroEntity.getNomeCorte(), cortesBarbeiroEntity.getPreco(), cortesBarbeiroEntity.getTempoMedio(), cortesBarbeiroEntity.getIdCorte());

        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Atualizado")
                .build(), HttpStatus.OK);
    }

    @PostMapping("/atualiza")
    ResponseEntity<Resposta> atualizaBarbeiro(@RequestBody BarbeiroEntity barbeiroEntity) throws IOException {

        BarbeiroEntity barbeiroEntityByIdUsuario = barbeiroDAO.findBarbeiroEntityByIdUsuario(barbeiroEntity.getIdUsuario());

        barbeariaService.getEndereco(barbeiroEntity);

        barbeiroDAO.updateBarbeiro(barbeiroEntity.getNomeBarbearia(), barbeiroEntity.getCep(), barbeiroEntity.getNumero(), barbeiroEntity.getTelefone(), barbeiroEntity.getCidade(), barbeiroEntity.getRua(), barbeiroEntity.getBairro(), barbeiroEntity.getUf(), barbeiroEntityByIdUsuario.getIdBarbeiro());

        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Atualizado")
                .build(), HttpStatus.OK);

    }

    @GetMapping("/busca/barbeiro")
    ResponseEntity<Resposta> buscaBarbeiro(@RequestParam(value = "barbearia") Integer id){


        BarbeiroEntity barbeiro = barbeiroDAO.findBarbeiroEntityByIdUsuario(id);

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
}
