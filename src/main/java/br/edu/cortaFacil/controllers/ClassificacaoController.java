package br.edu.cortaFacil.controllers;

import br.edu.cortaFacil.aux.Resposta;
import br.edu.cortaFacil.dao.Agenda;
import br.edu.cortaFacil.dao.Avaliacao;
import br.edu.cortaFacil.dao.Barbeiro;
import br.edu.cortaFacil.entity.AvaliacaoEntity;
import br.edu.cortaFacil.entity.ClassificacaoEntity;
import br.edu.cortaFacil.service.ClassificacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : agoliveira 02/2020
 */
@RestController
@RequestMapping("/classificacao")
@CrossOrigin(origins = "*")
@Slf4j
public class ClassificacaoController {

    @Autowired
    Agenda agendaDAO;

    @Autowired
    Barbeiro barbeiroDAO;

    @Autowired
    Avaliacao avaliacao;

    @Autowired
    ClassificacaoService classificacaoService;


    @GetMapping("/teste")
    public void teste(){


    }

    @GetMapping("/cliente/classificaBarbeiro")
    public ResponseEntity<Resposta> classificaBarbeiro(Integer idBarbeiro){

        ClassificacaoEntity classificacaoEntity = classificacaoService.classificaBarbearia(idBarbeiro);

        return new ResponseEntity<>(Resposta.builder()
                .object(classificacaoEntity)
                .build(), HttpStatus.OK);

    }

}
