package com.nogueira.julia.msuser.domain.entity;

import com.nogueira.julia.msuser.domain.dto.DadosFuncionario;
import com.nogueira.julia.msuser.domain.dto.DadosVotos;
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

    @Column(name="id_melhoria")
    private Long idMelhoria;



    public Funcionario(DadosFuncionario dados) {
        this.cpf = dados.cpf();

    }

    public Funcionario(DadosVotos dadosVotos){
        this.cpf = dadosVotos.getCpf();
        this.idMelhoria = dadosVotos.getIdMelhoria();

    }


}
