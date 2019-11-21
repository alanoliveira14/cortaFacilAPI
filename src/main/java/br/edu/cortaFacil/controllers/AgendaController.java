package br.edu.cortaFacil.controllers;


import br.edu.cortaFacil.aux.Error;
import br.edu.cortaFacil.aux.Resposta;
import br.edu.cortaFacil.dao.Agenda;
import br.edu.cortaFacil.entity.AgendaEntity;
import br.edu.cortaFacil.service.AgendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agenda")
@Slf4j
public class AgendaController {

    @Autowired
    Agenda agendaDAO;

    @Autowired
    AgendaService agendaService;

    @GetMapping("/consulta/agenda")
    ResponseEntity<Resposta> pesquisaHorarios(@RequestHeader(value = "barbeiro") Integer barbeiro, @RequestHeader(value = "data") String data){

        List<AgendaEntity> allByIdBarbeariaAndData = agendaDAO.findAllByIdBarbeariaAndData(barbeiro, data);

        if(allByIdBarbeariaAndData == null || allByIdBarbeariaAndData.isEmpty()){

            allByIdBarbeariaAndData = Collections.EMPTY_LIST;
        }


        return new ResponseEntity<>(Resposta.builder()
                .object(allByIdBarbeariaAndData)
                .build(), HttpStatus.OK);


    }

    @PostMapping("/cadastra/horario")
    ResponseEntity<Resposta> novoHorario(@RequestBody AgendaEntity agenda){

        try {

            agendaService.cadastraHorario(agenda);

        } catch (Exception e) {

            log.error("Erro ao cadastrar horario", e);

            return new ResponseEntity<>(Resposta.builder()
                        .erro(Error.builder()
                                .mensagem(e.getMessage())
                                .build())
                    .build(),
                    HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Horário cadastrado com sucesso!")
                .build(), HttpStatus.OK);
    }

}
