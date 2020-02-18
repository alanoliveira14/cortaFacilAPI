package br.edu.cortaFacil.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "agenda")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgenda")
    private Integer idAgenda;

    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "idBarbearia")
    private Integer idBarbearia;

    @Column(name = "idCorte")
    private Integer idCorte;

    @Column(name = "data")
    private String data;

    @Column(name = "horaInicio")
    private String horaInicio;

    @Column(name = "horaFim")
    private String horaFim;

    @Column(name = "flgAval")
    private Integer flgAval;

    private String nomeCliente;

    private String nomeCorte;

    private Double preco;

    private String nomeBarbearia;

}
