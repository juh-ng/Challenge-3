package br.julia.nogueira.msmelhoria.controller;

import br.julia.nogueira.msmelhoria.dto.DadosMelhoria;
import br.julia.nogueira.msmelhoria.dto.DadosVoto;
import br.julia.nogueira.msmelhoria.entity.Melhoria;
import br.julia.nogueira.msmelhoria.repository.MelhoriaRepository;
import br.julia.nogueira.msmelhoria.service.MelhoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("melhoria")
public class MelhoriaController {
    @Autowired
    private MelhoriaService service;

    @Autowired
    private MelhoriaRepository repository;





    @PostMapping
    @Transactional
    public ResponseEntity guardarMelhorias(@RequestBody @Valid DadosMelhoria dados, UriComponentsBuilder buider) {
        var propostas = service.cadastrarProposta(dados);
        var uri = buider.path("/melhoria/{id}").buildAndExpand(propostas.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosMelhoria(propostas));

    }


    @PutMapping("/votacao")
    @Transactional
    public ResponseEntity abrirVotacao(@RequestBody DadosVoto voto) throws IllegalAccessException {
      repository.getReferenceById(voto.getIdMelhoria());
         if(voto.getMin() == 0){
            Duration duracaoVotacao = Duration.ofMinutes(1);
            LocalDateTime agora = LocalDateTime.now();
            LocalDateTime horarioEncerramento = agora.plus(duracaoVotacao);

            if (agora.isAfter(horarioEncerramento)) {
                throw new RuntimeException("Votação encerrada");
            }
        }
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime horarioEncerramento = agora.plusMinutes(voto.getMin());

        if (agora.isAfter(horarioEncerramento)){
            throw new RuntimeException("Votação encerrada");
        }
        service.evitaVotosRepetidos(voto);
        service.atualizaVotos(voto);

    return ResponseEntity.ok().build();

    }

    @GetMapping("/propostas")
    public ResponseEntity<Page<DadosMelhoria>> listarPropostas(@PageableDefault(sort = {"id"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosMelhoria::new);
        return ResponseEntity.ok(page);

    }





}
