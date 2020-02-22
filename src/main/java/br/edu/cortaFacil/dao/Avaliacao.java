package br.edu.cortaFacil.dao;

import br.edu.cortaFacil.entity.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : agoliveira 02/2020
 */
public interface Avaliacao extends JpaRepository<AvaliacaoEntity, Integer> {
}
