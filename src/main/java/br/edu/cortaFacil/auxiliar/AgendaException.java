package br.edu.cortaFacil.auxiliar;

public class AgendaException extends Exception {

    //excecao personalizada usada dentro de API para mostrar que ocorreu um erro referente a agenda

    public AgendaException(String erro){
        super(erro);
    }

}
