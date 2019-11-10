package br.edu.cortaFacil.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUsuario")
    private Integer idUsuario;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    @Column(name = "nvlLogin")
    private Integer nvlLogin;

    @Column(name = "ativo")
    private Integer ativo;

    @Column(name = "token")
    private String token;

}
