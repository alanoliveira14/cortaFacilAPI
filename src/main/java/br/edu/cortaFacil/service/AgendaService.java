package br.edu.cortaFacil.service;

import br.edu.cortaFacil.aux.AgendaException;
import br.edu.cortaFacil.aux.Utils;
import br.edu.cortaFacil.dao.Agenda;
import br.edu.cortaFacil.dao.CortesBarbeiro;
import br.edu.cortaFacil.entity.AgendaEntity;
import br.edu.cortaFacil.entity.CortesBarbeiroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendaService {

    public AgendaService(Agenda agenda){

    }

    Utils utils;

    @Autowired
    Agenda agendaDAO;

    @Autowired
    CortesBarbeiro cortesBarbeiroDAO;

    public void cadastraHorario(AgendaEntity agendaEntity) throws Exception{

        CortesBarbeiroEntity corte = cortesBarbeiroDAO.findByIdCorte(agendaEntity.getIdCorte());



        Integer numeroDeCortesNoHorario = agendaDAO.findNumeroDeCortesNoHorario(agendaEntity.getHoraInicio(), corte.getTempoMedio(),agendaEntity.getData());

        if(numeroDeCortesNoHorario != null && numeroDeCortesNoHorario > 0){
            throw new AgendaException("Horário indisponível, já possui cortes agendados para o mesmo!");
        }else{
            agendaDAO.insertNovo(agendaEntity.getIdCliente(), agendaEntity.getIdBarbearia(), agendaEntity.getIdCorte(), agendaEntity.getData(), agendaEntity.getHoraInicio(), corte.getTempoMedio());
        }

    }

}
