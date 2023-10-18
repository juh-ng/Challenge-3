package br.julia.nogueira.msmelhoria.entity;

import br.julia.nogueira.msmelhoria.dto.MelhoriaDto;
import br.julia.nogueira.msmelhoria.dto.Voto;
import br.julia.nogueira.msmelhoria.dto.VotoTeste;
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
    @Enumerated(EnumType.STRING)
    private Voto Voto;
    @Column(name = "qt_votos")
    private Integer qtdVoto = 0;

    public Melhoria(Long id, String tipoMelhoria) {
        this.id = id;
        this.tipoMelhoria = tipoMelhoria;
    }

    public void atualizarVotos(VotoTeste votoTeste) {
        if(votoTeste.getVoto().equals(br.julia.nogueira.msmelhoria.dto.Voto.APROVAR)){
           qtdVoto ++;
        }

    }

}


