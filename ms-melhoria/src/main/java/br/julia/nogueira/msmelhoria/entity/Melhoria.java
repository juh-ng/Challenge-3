package br.julia.nogueira.msmelhoria.entity;

import br.julia.nogueira.msmelhoria.dto.Voto;
import br.julia.nogueira.msmelhoria.dto.DadosVoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="melhorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Melhoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "proposta_melhoria")
    private String tipoMelhoria;

//    @Enumerated(EnumType.STRING)
//    private Voto Voto;

    @Column(name = "qt_votos")
    private Integer qtdVoto = 0;

    @Column(name="esta_ativo")
    private Boolean ativo;

    public Melhoria(Long id, String tipoMelhoria) {
        this.id = id;
        this.tipoMelhoria = tipoMelhoria;
        this.ativo = true;
    }

    public void atualizarVotos(DadosVoto dadosVoto) {
        if(dadosVoto.getVoto().equals(Voto.APROVAR)){
           qtdVoto ++;
        }
    }

}


