package br.julia.nogueira.msmelhoria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosVoto {


    private Integer qtdVoto = 0;

    private Long idMelhoria;

    private Voto voto;
}
