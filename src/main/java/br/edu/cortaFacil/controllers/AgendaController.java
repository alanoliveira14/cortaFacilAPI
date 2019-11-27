package br.edu.cortaFacil.controllers;


import br.edu.cortaFacil.aux.Error;
import br.edu.cortaFacil.aux.Resposta;
import br.edu.cortaFacil.dao.Agenda;
import br.edu.cortaFacil.dao.Barbeiro;
import br.edu.cortaFacil.dao.Cliente;
import br.edu.cortaFacil.dao.CortesBarbeiro;
import br.edu.cortaFacil.entity.AgendaEntity;
import br.edu.cortaFacil.entity.BarbeiroEntity;
import br.edu.cortaFacil.entity.ClienteEntity;
import br.edu.cortaFacil.entity.CortesBarbeiroEntity;
import br.edu.cortaFacil.service.AgendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agenda")
@Slf4j
public class AgendaController {

    /*
    *
    * Controller de Agenda - responsavel por realizar todas as ações referentes ao agendamento
    *
    * possui duas rotas dentro dele:
    * - rota que pesquisa horarios
    * - rota que realiza um novo agendamento
    * */

    /*
    * anotações utilizadas, representadas a partir do @
    *
    * @Autowired - quer dizer que aquele objeto sera instanciado a partir de injeção de dependencia (por isso aqui em
    * nenhum lugar de se vê códigos do tipo "NomeDaClasse classe = new Classe()")
    *
    * @RequestMapping - mappings sao as rotas, caminhos, os '/qualquercoisa' que tem em todas as urls, nesse caso ele mostra
    * que é o caminho base de tudo, toda rota dentro deste controller tera aquele início
    *
    * @GetMapping - quer dizer que aquele mapa é de metodo GET (ou seja, você irá buscar informações naquela rota, ela tem a intenção
    * de realizar uma consulta por exemplo)
    *
    * @PostMapping - quer dizer que aquele mapata é de metodo POST (ou seja, voce irá mandar informações naquela rota, ela tem a intenção
    * de realizar um insert por exemplo)
    * */

    @Autowired
    Agenda agendaDAO;

    @Autowired
    AgendaService agendaService;

    @Autowired
    CortesBarbeiro cortesBarbeiroDAO;

    @Autowired
    Cliente clienteDAO;

    @Autowired
    Barbeiro barbeiroDAO;

    @GetMapping("/consulta/agenda")
    ResponseEntity<Resposta> pesquisaHorarios(@RequestHeader(value = "barbeiro") Integer barbeiro, @RequestHeader(value = "data") String data, @RequestParam String tipo){
        BarbeiroEntity barbeiroEntityByIdUsuario = null;

        if("barbeiro".equals(tipo)){
            barbeiroEntityByIdUsuario  = barbeiroDAO.findBarbeiroEntityByIdUsuario(barbeiro);

            barbeiro = barbeiroEntityByIdUsuario.getIdBarbeiro();

        }

        List<AgendaEntity> allByIdBarbeariaAndData = agendaDAO.findAllByIdBarbeariaAndDataOrderByHoraInicioAsc(barbeiro, data);

        if(allByIdBarbeariaAndData == null || allByIdBarbeariaAndData.isEmpty()){

            allByIdBarbeariaAndData = Collections.EMPTY_LIST;
        }

        if("barbeiro".equals(tipo)) {

            this.completaRespostaAgenda(allByIdBarbeariaAndData);
        }

        return new ResponseEntity<>(Resposta.builder()
                .object(allByIdBarbeariaAndData)
                .build(), HttpStatus.OK);


    }

    @PostMapping("/cadastra/horario")
    ResponseEntity<Resposta> novoHorario(@RequestBody AgendaEntity agenda){

        ClienteEntity byIdUsuario = clienteDAO.findByIdUsuario(agenda.getIdCliente());

        agenda.setIdCliente(byIdUsuario.getIdCliente());

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

    public void completaRespostaAgenda(List<AgendaEntity> agenda){

        for (AgendaEntity horario: agenda) {

            CortesBarbeiroEntity byIdCorte = cortesBarbeiroDAO.findByIdCorte(horario.getIdCorte());

            horario.setNomeCorte(byIdCorte.getNomeCorte());
            horario.setPreco(byIdCorte.getPreco());

            ClienteEntity byIdCliente = clienteDAO.findByIdCliente(horario.getIdCliente());

            horario.setNomeCliente(Optional.ofNullable(byIdCliente.getNome()).orElse(""));

        }

    }

    public void completaRespostaAgendaCliente(List<AgendaEntity> agenda){

        for (AgendaEntity horario: agenda) {

            CortesBarbeiroEntity byIdCorte = cortesBarbeiroDAO.findByIdCorte(horario.getIdCorte());

            horario.setNomeCorte(byIdCorte.getNomeCorte());
            horario.setPreco(byIdCorte.getPreco());

        }

    }


    @GetMapping("/lista/futuros")
    public ResponseEntity<Resposta> listaFuturosCortes(@RequestParam Integer idUsuario){

        ClienteEntity byIdUsuario = clienteDAO.findByIdUsuario(idUsuario);

        List<AgendaEntity> cortesFuturos = agendaDAO.findCortesFuturos(byIdUsuario.getIdCliente());

        if(cortesFuturos != null && !cortesFuturos.isEmpty()){

            this.completaRespostaAgendaCliente(cortesFuturos);

            return new ResponseEntity<>(Resposta.builder()
                    .object(cortesFuturos)
                    .build(), HttpStatus.OK);
        }

        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Sem cortes futuros")
                .build(), HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/apaga/agendamento")
    public ResponseEntity<Resposta> apagarAgendamento(@RequestParam Integer idAgendamento){

        agendaDAO.deleteByIdAgenda(idAgendamento);

        return new ResponseEntity<>(Resposta.builder()
                .mensagem("Agendamento cancelado")
                .build(), HttpStatus.OK);

    }

}
