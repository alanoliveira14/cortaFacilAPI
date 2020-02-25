package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author : agoliveira 02/2020
 */
public interface Avaliacao extends JpaRepository<AvaliacaoEntity, Integer> {

    @Query(value = "select count(nota) as idAval, nota from avaliacao av inner JOIN agenda ag inner join barbeiro on av.idAgenda = ag.idAgenda and ag.idBarbearia = barbeiro.idBarbeiro and barbeiro.idBarbeiro = :idBarbeiro group by nota", nativeQuery = true)
    List<Integer[]> totalDeAvaliacoesPorNota(@Param("idBarbeiro") Integer idBarbeiro);

    @Query(value = "SELECT av.* FROM avaliacao av inner JOIN agenda ag inner join barbeiro on av.idAgenda = ag.idAgenda and ag.idBarbearia = barbeiro.idBarbeiro and barbeiro.idBarbeiro = :idBarbeiro order by idAval desc limit 10;", nativeQuery = true)
    List<AvaliacaoEntity> listaComentariosDaBarbearia(@Param("idBarbeiro") Integer idBarbeiro);

}
