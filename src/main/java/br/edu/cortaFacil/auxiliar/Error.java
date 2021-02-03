package br.edu.cortaFacil.auxiliar;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Error {

    //entidade usada dentro da resposta pra mostrar pro frontEnd (tela) que ocorreu um erro e qual foi

    String mensagem;

}
