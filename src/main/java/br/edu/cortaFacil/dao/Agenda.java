package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Agenda extends JpaRepository<AgendaEntity, Integer> {

    /*
    *
    * DAO de agenda, usado para fazer as acoes de agenda junto ao banco de dados
    *
    * esse é o unico DAO onde as queries foram complexas o suficiente, para se fazer necessário escrever na mao
    *
    * usa-se o @Query para dizer pro SpringData que passaremos uma query personaliada para aquele método
    * no meio da query, as palavras precedidas por ':' são parametros que serao trocados no momento da execucao pelas variaveis
    * que estão em seguidas aos @Param, que possuem o mesmo nome
    *
    * os métodos que nao possuem query em cima, são aqueles em que o Data gera automaticamente a query, bastando apenas dizer oque se quer fazer
    * no caso todos sao find, que indica que serão selects
    *
    * os save() querem dizer que serao feitos insert, eles estao direto nos controllers ou dentro dos services e sao metodos
    * padroes do Data
    *
    * */

    List<AgendaEntity> findAllByIdBarbeariaAndDataOrderByHoraInicioAsc(Integer idBarbeiro, String data);

    @Query(value = "select count(*) from agenda where (:horaCorte between horaInicio and horaFim) or ((select addtime(:horaCorte, sec_to_time(:tempoCorte * 60) ) ) between horaInicio and horaFim) and data = :dataCorte", nativeQuery = true)
    Integer findNumeroDeCortesNoHorario(@Param("horaCorte") String horaCorte, @Param("tempoCorte") Integer tempoCorte, @Param("dataCorte") String dataCorte);


    @Query(value = "select * from agenda where idBarbearia = :idBarbeiro and data = :dataCorte and (:horaCorte between horaInicio and horaFim) or ((select addtime(:horaCorte, sec_to_time(:tempoCorte * 60) ) ) between horaInicio and horaFim and data= :dataCorte);", nativeQuery = true)
    Integer findNumeroDeCortesNoHorarioDoBarbeiro(@Param("horaCorte") String horaCorte, @Param("tempoCorte") Integer tempoCorte, @Param("dataCorte") String dataCorte, @Param("idBarbeiro") Integer idBarbeiro);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO agenda(idCliente, idBarbearia, idCorte, data, horaInicio, horaFim) values(:idCliente, :idBarbearia, :idCorte, :dataCorte, :horaInicio, (select addtime(:horaInicio, sec_to_time(:tempoCorte * 60) ) ) ) ", nativeQuery = true)
    void insertNovo(@Param("idCliente") Integer idCliente, @Param("idBarbearia") Integer idBarbearia, @Param("idCorte") Integer idCorte, @Param("dataCorte") String dataCorte, @Param("horaInicio") String horaIniciio, @Param("tempoCorte") Integer tempoCorte);


    @Query(value = "select * from agenda where idCliente = :idcliente and data >= now() order by data asc , horaInicio asc", nativeQuery = true)
    List<AgendaEntity> findCortesFuturos(@Param("idcliente") Integer idCliente);


    @Modifying
    @Transactional
    void deleteByIdAgenda(Integer idAgenda);
}
