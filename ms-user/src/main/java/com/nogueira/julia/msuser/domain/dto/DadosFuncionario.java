package com.nogueira.julia.msuser.domain.dto;

import com.nogueira.julia.msuser.domain.entity.Funcionario;
import org.hibernate.validator.constraints.br.CPF;


public record DadosFuncionario(

        Long id,

        @CPF(message = "nao_pode_votar")
        String cpf) {


        public DadosFuncionario(Funcionario funcionario) {
                this(funcionario.getId(), funcionario.getCpf());
        }






}
