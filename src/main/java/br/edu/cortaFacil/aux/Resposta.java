package br.edu.cortaFacil.aux;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Resposta {

    String mensagem;
    Object object;

}
