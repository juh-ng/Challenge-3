package com.nogueira.julia.msuser.domain.service;

import com.nogueira.julia.msuser.client.VotoClient;
import com.nogueira.julia.msuser.domain.dto.DadosFuncionario;
import com.nogueira.julia.msuser.domain.dto.DadosVotos;
import com.nogueira.julia.msuser.domain.dto.Voto;
import com.nogueira.julia.msuser.domain.entity.Funcionario;
import com.nogueira.julia.msuser.domain.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private DadosVotos dadosVotos;

    @Autowired
    private VotoClient client;

    public Funcionario cadastrarFuncionario(DadosFuncionario dados) throws IllegalAccessException {
        Optional<Funcionario> existingFuncionario = repository.findByCpf(dados.cpf());
        if(existingFuncionario.isPresent()){
            throw new IllegalAccessException("cpf já cadastrado: nao_pode_votar");
        }
        var user = new Funcionario(dados);
        return repository.save(user);
    }

    public void cadastrarVoto(DadosVotos dados){
        client.registraVotacao(dados);
           if(dados.getVoto() == Voto.APROVAR){
                 int votoCont = dados.getQtdVoto();
                 votoCont++;
                 dadosVotos.setQtdVoto(votoCont);
             }
          else{
              throw new EntityNotFoundException();
            }
    }

    public Funcionario evitaVotosRepetidos(DadosVotos dados) throws IllegalAccessException {
        var existingFuncionario = repository.existsByCpf(dados.getCpf());
        var propostaMelhoria = repository.existsById(dados.getIdMelhoria());

        if(existingFuncionario && propostaMelhoria ){
            throw new RuntimeException("Funcionario já votou nessa proposta");
        }

        Funcionario votoFuncionario = new Funcionario(dados);
        return repository.save(votoFuncionario);
    }


}
