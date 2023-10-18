package br.julia.nogueira.msmelhoria.service;

import br.julia.nogueira.msmelhoria.dto.DadosMelhoria;
import br.julia.nogueira.msmelhoria.entity.Melhoria;
import br.julia.nogueira.msmelhoria.repository.MelhoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MelhoriaService {



    @Autowired
    private final MelhoriaRepository repository;

    public MelhoriaService(MelhoriaRepository repository) {
        this.repository = repository;
    }

    public Melhoria cadastrarProposta(DadosMelhoria dados){
        var melhorias = new Melhoria(null, dados.tipoMelhoria());
        return repository.save(melhorias);
    }











}
