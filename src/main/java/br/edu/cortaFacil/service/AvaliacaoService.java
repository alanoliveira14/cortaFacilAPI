package br.edu.cortaFacil.service;

import br.edu.cortaFacil.auxiliar.AvaliacaoException;
import br.edu.cortaFacil.dao.Agenda;
import br.edu.cortaFacil.dao.Avaliacao;
import br.edu.cortaFacil.entity.AvaliacaoEntity;
import br.edu.cortaFacil.entity.NotasDeAvaliacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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


    public List<NotasDeAvaliacao> notasDeAvaliacao(Integer idBarbearia){

        List<Integer[]> notas = avaliacaoDAO.totalDeAvaliacoesPorNota(idBarbearia);

        if (notas == null){
            return null;
        }

        return preparaPorcentagem(notas);
    }

    private List<NotasDeAvaliacao> preparaPorcentagem(List<Integer[]> notas){

        Integer totalDeAvaliacoes = 0;

        for (Integer[] nota:notas) {
            totalDeAvaliacoes += nota[0];
        }

        List<NotasDeAvaliacao> notasDeAvaliacao = new ArrayList<>();


        for (Integer[] nota:notas) {
            NotasDeAvaliacao n = NotasDeAvaliacao.builder()
                    .nota(nota[1])
                    .quantidadeNota(nota[0])
                    .porcentagem((nota[0] * 100)/totalDeAvaliacoes)
                    .build();
            notasDeAvaliacao.add(n);
        }

        return notasDeAvaliacao;
    }

}
