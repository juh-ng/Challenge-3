package br.julia.nogueira.msmelhoria.repository;

import br.julia.nogueira.msmelhoria.entity.Melhoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MelhoriaRepository extends JpaRepository <Melhoria, Long>{






     Page<Melhoria> findAllByAtivoTrue(Pageable paginacao);
}
