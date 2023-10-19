package com.nogueira.julia.msuser.domain.repository;

import com.nogueira.julia.msuser.domain.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {
}
