package br.julia.nogueira.msmelhoria.entity;

import br.julia.nogueira.msmelhoria.dto.DadosMelhoria;
import br.julia.nogueira.msmelhoria.dto.Voto;
import br.julia.nogueira.msmelhoria.dto.DadosVoto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="melhorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Melhoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "proposta_melhoria")
    private String tipoMelhoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="cpf")
    private VotoPorFuncionario votoPorFuncionario;


    @Column(name = "qt_votos")
    private Integer qtdVoto = 0;



    public Melhoria(Long id, String tipoMelhoria) {
        this.id = id;
        this.tipoMelhoria = tipoMelhoria;

    }

    public Melhoria(DadosMelhoria dadosCadastro) {
    }






}


