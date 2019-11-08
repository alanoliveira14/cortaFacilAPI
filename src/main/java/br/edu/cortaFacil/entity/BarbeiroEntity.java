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
@Table(name = "barbeiro")
public class BarbeiroEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBarbeiro")
    private Integer idBarbeiro;

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "nomeResponsavel")
    private String nomeResponsavel;

    @Column(name = "nomeBarbearia")
    private String nomeBarbearia;

    @Column(name = "CEP")
    private Integer cep;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "cidade")
    private String cidade;

}



