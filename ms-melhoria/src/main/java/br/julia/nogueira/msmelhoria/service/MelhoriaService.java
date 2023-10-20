package br.julia.nogueira.msmelhoria.service;

import br.julia.nogueira.msmelhoria.dto.DadosMelhoria;
import br.julia.nogueira.msmelhoria.dto.DadosVoto;
import br.julia.nogueira.msmelhoria.dto.Voto;
import br.julia.nogueira.msmelhoria.entity.Melhoria;
import br.julia.nogueira.msmelhoria.entity.VotoPorFuncionario;
import br.julia.nogueira.msmelhoria.repository.MelhoriaRepository;
import br.julia.nogueira.msmelhoria.repository.VotoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MelhoriaService {


    @Autowired
    private final MelhoriaRepository repository;

    @Autowired
    private final VotoFuncionarioRepository funcionarioRepository;




    public MelhoriaService(MelhoriaRepository repository, VotoFuncionarioRepository votoFuncionarioRepository) {
        this.repository = repository;
        this.funcionarioRepository = votoFuncionarioRepository;
    }

    public Melhoria cadastrarProposta(DadosMelhoria dados) {
        var melhorias = new Melhoria(null, dados.tipoMelhoria());
        return repository.save(melhorias);
    }

    public VotoPorFuncionario evitaVotosRepetidos(DadosVoto dados) throws IllegalAccessException {
        var existingFuncionario = funcionarioRepository.existsByCpf(dados.getCpf());
        var propostaMelhoria = funcionarioRepository.existsById(dados.getIdMelhoria());

        if(existingFuncionario.describeConstable().isPresent() & propostaMelhoria ){
            throw new RuntimeException("Funcionario já votou nessa proposta");
        }

        VotoPorFuncionario votoFuncionario = new VotoPorFuncionario(dados);
        return funcionarioRepository.save(votoFuncionario);
    }

    public void atualizaVotos(DadosVoto dadosVoto) {
        Melhoria melhoria = repository.findById(dadosVoto.getIdMelhoria())
                .orElseThrow(() -> new RuntimeException("Proposta de melhoria não encontrada"));

        if (dadosVoto.getVoto().equals(Voto.APROVAR)) {
             melhoria.setContagemVotos(dadosVoto);
            repository.save(melhoria);
        } else if (dadosVoto.getVoto().equals(Voto.REJEITAR)) {
            throw new RuntimeException("Proposta rejeitada");
        }
    }




}













