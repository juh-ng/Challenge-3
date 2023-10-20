package com.nogueira.julia.msuser.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DadosVotos {
    private Integer qtdVoto = 0;

    private Long idMelhoria;

    private Voto voto;

    private Integer min;

    @CPF(message = "nao_pode_votar")
    private String cpf;




}
