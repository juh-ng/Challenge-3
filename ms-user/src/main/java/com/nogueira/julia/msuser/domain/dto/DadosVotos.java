package com.nogueira.julia.msuser.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DadosVotos {
    private Integer qtdVoto = 0;

    private Long idMelhoria;

    private Voto voto;


}
