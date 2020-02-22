package br.edu.cortaFacil.service;

import br.edu.cortaFacil.dao.Agenda;
import br.edu.cortaFacil.dao.Barbeiro;
import br.edu.cortaFacil.entity.ClassificacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : agoliveira 02/2020
 */
@Component
public class ClassificacaoService {

    public enum CLASSIFICACAO_BARBEIRO {
        BRONZE("BRONZE"),
        PRATA("PRATA"),
        OURO("OURO");

        CLASSIFICACAO_BARBEIRO(String classificacao) {
        }
    }

    ClassificacaoService (Barbeiro barbeiro, Agenda agenda){};

    @Autowired
    Barbeiro barbeiroDAO;

    @Autowired
    Agenda agendaDAO;

    public ClassificacaoEntity classificaBarbearia(Integer idBarbearia){

        Integer totalDeCortesBarbeiro = barbeiroDAO.totalDeCortesBarbeiro(idBarbearia);

        Double notaMediaBarbeiro = barbeiroDAO.notaMediaBarbeiro(idBarbearia);

        Integer mediaDeCortesGeral = agendaDAO.mediaDeCortesGeral();

        ClassificacaoEntity classificacao = ClassificacaoEntity.builder()
                .idBarbeiro(idBarbearia)
                .build();

        classificaBarbeiro(classificacao, totalDeCortesBarbeiro, notaMediaBarbeiro, mediaDeCortesGeral);

        return classificacao;
    }


    private void classificaBarbeiro(ClassificacaoEntity classificacaoEntity, Integer totalDeCortes, Double notaMediaBarbeiro, Integer mediaCortesGeral){

        totalDeCortes = totalDeCortes == null ? 0 : totalDeCortes;
        notaMediaBarbeiro = notaMediaBarbeiro == null ? 0.0 : notaMediaBarbeiro;

        if(totalDeCortes <= ((int) mediaCortesGeral * 0.7)){
            classificacaoEntity.setClassificacao(CLASSIFICACAO_BARBEIRO.BRONZE.toString());

        } else if(totalDeCortes <= ((int) mediaCortesGeral * 0.9)){

            if(notaMediaBarbeiro >= 4.0){
                classificacaoEntity.setClassificacao(CLASSIFICACAO_BARBEIRO.PRATA.toString());

            }else{
                classificacaoEntity.setClassificacao(CLASSIFICACAO_BARBEIRO.BRONZE.toString());

            }

        }else {

            if (notaMediaBarbeiro <= 3.5){
                classificacaoEntity.setClassificacao(CLASSIFICACAO_BARBEIRO.BRONZE.toString());

            }else if(notaMediaBarbeiro < 4.3){
                classificacaoEntity.setClassificacao(CLASSIFICACAO_BARBEIRO.PRATA.toString());

            }else{
                classificacaoEntity.setClassificacao(CLASSIFICACAO_BARBEIRO.OURO.toString());

            }

        }

        ranqueia(classificacaoEntity);

        classificacaoEntity.setNota(notaMediaBarbeiro);
    }

    private void ranqueia(ClassificacaoEntity classificacaoEntity){
        if(classificacaoEntity.getClassificacao().equals(CLASSIFICACAO_BARBEIRO.OURO.toString())){
            classificacaoEntity.setRanking(1);
        }else if(classificacaoEntity.getClassificacao().equals(CLASSIFICACAO_BARBEIRO.PRATA.toString())){
            classificacaoEntity.setRanking(2);
        }else {
            classificacaoEntity.setRanking(3);
        }
    }

    /*
     *
     * totalDeCortes <= 70% mediaGeral -> bronze
     * totalDeCortes  <=90 % mediaGeral {
     *   notaMedia >=4 -> Prata
     *   Bronze
     * }
     *
     * else{
     *   media <= 3.5 -> Bronze
     * else if media < 4.3 -> Prata
     * else -> Ouro
     * }
     * */
}
