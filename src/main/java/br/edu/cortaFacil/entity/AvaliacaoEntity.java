package br.edu.cortaFacil.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author : agoliveira 02/2020
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avaliacao")
public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAval")
    private Integer idAval;

    @Column(name = "idAgenda")
    private Integer idAgenda;

    @Column(name = "nota")
    private Integer nota;

    @Column(name = "comentario")
    private String comentario;

}
