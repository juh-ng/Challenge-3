package com.nogueira.julia.msuser.domain.dto;


import com.nogueira.julia.msuser.domain.entity.Funcionario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record DadosFuncionario(

        Long id,

        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "nao_pode_votar")
        @CPF
        String cpf) {


        public DadosFuncionario(Funcionario funcionario) {
                this(funcionario.getId(), funcionario.getCpf());
        }


}
