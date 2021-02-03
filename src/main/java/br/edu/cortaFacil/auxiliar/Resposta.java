package br.edu.cortaFacil.auxiliar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Resposta {

    /*classe que é utilizada como resposta para a API
    * foi criada para termos um padrão de resposta em questão de estrutura, assim todas teriam o mesmo formato
    * facilitando assim a implementacao nas telas
    * */

    String mensagem;
    Object object;
    Error erro;

}
