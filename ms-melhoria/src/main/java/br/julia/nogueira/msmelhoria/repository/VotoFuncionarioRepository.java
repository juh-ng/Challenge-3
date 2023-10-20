package br.julia.nogueira.msmelhoria.repository;

import br.julia.nogueira.msmelhoria.entity.VotoPorFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface VotoFuncionarioRepository extends JpaRepository<VotoPorFuncionario, Long> {



    Boolean existsByCpf(String cpf);
}
