package br.edu.cortaFacil.entity;

import lombok.*;

/**
 * @author : agoliveira 02/2020
 */

@Getter
@Setter
public class BarbeiroComClassificacao extends BarbeiroEntity{

    public BarbeiroComClassificacao(BarbeiroEntity barbeiroEntity){
        super.setIdBarbeiro(barbeiroEntity.getIdBarbeiro());
        super.setBairro(barbeiroEntity.getBairro());
        super.setCidade(barbeiroEntity.getCidade());
        super.setNomeBarbearia(barbeiroEntity.getNomeBarbearia());
        super.setRua(barbeiroEntity.getRua());
        super.setUf(barbeiroEntity.getUf());
        super.setCep(barbeiroEntity.getCep());
        super.setDescricao(barbeiroEntity.getDescricao());
        super.setNumero(barbeiroEntity.getNumero());
        super.setTelefone(barbeiroEntity.getTelefone());
    }

    private ClassificacaoEntity classificacaoEntity;


}
