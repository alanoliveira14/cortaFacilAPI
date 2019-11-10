package br.edu.cortaFacil.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cortesBarbeiro")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CortesBarbeiroEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCorte")
    private Integer idCorte;

    @Column(name = "idBarbearia")
    private Integer idBarbearia;

    @Column(name = "nomeCorte")
    private String nomeCorte;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "tempoMedio")
    private Integer tempoMedio;

    @Column(name = "descricao")
    private String descricao;
}
