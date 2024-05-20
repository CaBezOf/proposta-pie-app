package com.ProjetoC.propostapieapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropostaRequestDto {

    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String renda;
    private String valorSolicitado;
    private int prazoPagamento;

}
