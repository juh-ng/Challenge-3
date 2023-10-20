package com.nogueira.julia.msuser.domain.repository;

import com.nogueira.julia.msuser.domain.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);

    Boolean existsByCpf(String cpf);
}
