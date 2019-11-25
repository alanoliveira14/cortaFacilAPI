package br.edu.cortaFacil.aux;

public class UsuarioIndisponivelException extends Exception{

    //excecao personalizada usada dentro de API para mostrar que o nome de usuario ja esta em uso


    public UsuarioIndisponivelException(String erro){
        super(erro);
    }

}
