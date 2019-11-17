package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Agenda extends JpaRepository<AgendaEntity, Integer> {

    List<AgendaEntity> findAllByIdBarbeariaAndData(Integer idBarbeiro, String data);

    @Query(value = "select count(*) from agenda where (:horaCorte between horaInicio and horaFim) or ((select addtime(:horaCorte, sec_to_time(:tempoCorte * 60) ) ) between horaInicio and horaFim) and data = :dataCorte", nativeQuery = true)
    Integer findNumeroDeCortesNoHorario(@Param("horaCorte") String horaCorte, @Param("tempoCorte") Integer tempoCorte, @Param("dataCorte") String dataCorte);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO agenda(idCliente, idBarbearia, idCorte, data, horaInicio, horaFim) values(:idCliente, :idBarbearia, :idCorte, :dataCorte, :horaInicio, (select addtime(:horaInicio, sec_to_time(:tempoCorte * 60) ) ) ) ", nativeQuery = true)
    void insertNovo(@Param("idCliente") Integer idCliente, @Param("idBarbearia") Integer idBarbearia, @Param("idCorte") Integer idCorte, @Param("dataCorte") String dataCorte, @Param("horaInicio") String horaIniciio, @Param("tempoCorte") Integer tempoCorte);

}
