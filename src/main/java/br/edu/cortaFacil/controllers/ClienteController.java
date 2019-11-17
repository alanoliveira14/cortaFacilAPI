package br.edu.cortaFacil.controllers;

import br.edu.cortaFacil.aux.Error;
import br.edu.cortaFacil.aux.Resposta;
import br.edu.cortaFacil.dao.Cliente;
import br.edu.cortaFacil.entity.ClienteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@Slf4j
public class ClienteController {

    @Autowired
    Cliente clienteDAO;

    @PostMapping("/cadastrar")
    ResponseEntity<Resposta> novoCliente(ClienteEntity clienteEntity){

        try {
            clienteDAO.save(clienteEntity);
        }catch (Exception e){
            return new ResponseEntity<>(Resposta.builder()
                    .erro(Error.builder()
                            .mensagem("Erro ao cadastrar cliente")
                            .build())
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Cliente cadastrado com sucesso!")
                .build(), HttpStatus.OK);

    }


}