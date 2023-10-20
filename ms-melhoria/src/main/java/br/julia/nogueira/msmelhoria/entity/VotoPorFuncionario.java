package br.julia.nogueira.msmelhoria.entity;

import br.julia.nogueira.msmelhoria.dto.DadosVoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="votos_funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="idFuncionario")
public class VotoPorFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    @ManyToOne
    @JoinColumn(name="id_melhoria")
    private Melhoria melhoria;


    private String cpf;



    public VotoPorFuncionario(DadosVoto dados) {
        this.cpf = dados.getCpf();

    }
}
