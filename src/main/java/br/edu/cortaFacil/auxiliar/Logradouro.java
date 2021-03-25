package br.edu.cortaFacil.auxiliar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Logradouro {

    //entidade que representa a resposta do viaCep, na hora que procuramos um endereço

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;

}
