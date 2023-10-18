package br.julia.nogueira.msmelhoria.dto;


import br.julia.nogueira.msmelhoria.entity.Melhoria;


public record DadosMelhoria(Long id, String tipoMelhoria, Integer qtdVoto) {

    public DadosMelhoria(Melhoria melhoria){
        this(melhoria.getId(), melhoria.getTipoMelhoria(), melhoria.getQtdVoto());
    }


}
