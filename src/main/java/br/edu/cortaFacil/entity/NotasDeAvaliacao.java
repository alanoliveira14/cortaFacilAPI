package br.edu.cortaFacil.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : agoliveira 02/2020
 */

@Getter
@Setter
@Builder
public class NotasDeAvaliacao {

    private Integer quantidadeNota;
    private Integer nota;
    private Integer porcentagem;

}
