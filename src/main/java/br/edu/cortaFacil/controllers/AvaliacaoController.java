package br.edu.cortaFacil.controllers;

import br.edu.cortaFacil.aux.Error;
import br.edu.cortaFacil.aux.Resposta;
import br.edu.cortaFacil.dao.Agenda;
import br.edu.cortaFacil.dao.Avaliacao;
import br.edu.cortaFacil.entity.AvaliacaoEntity;
import br.edu.cortaFacil.service.AvaliacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : agoliveira 02/2020
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/avaliacao")
@Slf4j
public class AvaliacaoController {

    @Autowired
    Avaliacao avaliacaoDAO;

    @Autowired
    Agenda agendaDAO;

    @Autowired
    AvaliacaoService avaliacaoService;

    @PostMapping("/nova/avaliacao")
    ResponseEntity<Resposta> novaAvaliacao(@RequestBody AvaliacaoEntity avaliacao){

        try {
            avaliacaoService.insereAvaliacao(avaliacao);
        }catch (Exception e){

            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem(e.getMessage())
                            .build())
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Avaliação realizada com sucesso!")
                .build(), HttpStatus.OK);
    }

}
