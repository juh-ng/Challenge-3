package br.julia.nogueira.msmelhoria.dto;


import br.julia.nogueira.msmelhoria.entity.Melhoria;


public record MelhoriaDto(Long id, String tipoMelhoria,  Integer qtdVoto, Voto voto) {

    public MelhoriaDto(Melhoria melhoria){
        this(melhoria.getId(), melhoria.getTipoMelhoria(), melhoria.getQtdVoto(),melhoria.getVoto());
    }


}
