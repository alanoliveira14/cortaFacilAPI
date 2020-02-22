package br.edu.cortaFacil.service;

import br.edu.cortaFacil.aux.AvaliacaoException;
import br.edu.cortaFacil.dao.Agenda;
import br.edu.cortaFacil.dao.Avaliacao;
import br.edu.cortaFacil.entity.AvaliacaoEntity;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : agoliveira 02/2020
 */
@Component
@Slf4j
public class AvaliacaoService {

    public AvaliacaoService(Avaliacao avaliacaoDAO, Agenda agendaDAO){

    }

    @Autowired
    Avaliacao avaliacaoDAO;

    @Autowired
    Agenda agendaDAO;


    public void insereAvaliacao(AvaliacaoEntity avaliacaoEntity) throws Exception{

        try {
            updateFlag(avaliacaoEntity);

            avaliacaoDAO.save(avaliacaoEntity);

        }catch (Exception e){
            log.error("Erro ao avaliar!" , e);

            throw new AvaliacaoException("Erro ao realizar avaliação");
        }
    }


    private void updateFlag(AvaliacaoEntity avaliacaoEntity){
        agendaDAO.updateFlagAgenda(avaliacaoEntity.getIdAgenda());
    }


}
