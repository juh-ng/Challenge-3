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

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private DadosVotos dadosVotos;

    @Autowired
    private VotoClient client;

    public Funcionario cadastrarFuncionario(DadosFuncionario dados){
        var user = new Funcionario(dados);
        return repository.save(user);
    }

    public void cadastrarVoto(DadosVotos dados){
        client.abreVotacao(dados);
           if(dados.getVoto() == Voto.APROVAR){
                 int votoCont = dados.getQtdVoto();
                 votoCont++;
                 dadosVotos.setQtdVoto(votoCont);
             }
          else{
              throw new EntityNotFoundException();
            }
    }


}
