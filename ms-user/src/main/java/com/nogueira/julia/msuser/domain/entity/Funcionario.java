package com.nogueira.julia.msuser.domain.entity;

import com.nogueira.julia.msuser.domain.dto.DadosFuncionario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name="funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    @Column(name="esta_ativo")
    private Boolean ativo;



    public Funcionario(DadosFuncionario dados) {
        this.cpf = dados.cpf();
        this.ativo = true;
    }
}
