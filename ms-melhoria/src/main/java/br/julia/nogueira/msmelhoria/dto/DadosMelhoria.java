package br.julia.nogueira.msmelhoria.dto;


import br.julia.nogueira.msmelhoria.entity.Melhoria;
import jakarta.validation.constraints.NotBlank;


public record DadosMelhoria(
        Long id,
        @NotBlank(message = "Informe uma proposta")
        String tipoMelhoria,
        Integer qtdVoto) {

    public DadosMelhoria(Melhoria melhoria){
        this(melhoria.getId(), melhoria.getTipoMelhoria(), melhoria.getQtdVoto());
    }



}
