package br.edu.cortaFacil.entity;

import lombok.*;

/**
 * @author : agoliveira 02/2020
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassificacaoEntity {

    private String classificacao;
    private double nota;
    private Integer idBarbeiro;
    private Integer ranking;


}
