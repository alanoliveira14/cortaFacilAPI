package br.edu.cortaFacil.controllers;


import br.edu.cortaFacil.aux.Resposta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/agenda")
public class AgendaController {

    @GetMapping("/consulta/agenda")
    ResponseEntity<Resposta> pesquisaHorarios(@RequestParam Integer barber){

    return null;


    }

}
