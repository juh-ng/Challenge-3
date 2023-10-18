package br.julia.nogueira.msmelhoria.controller;

import br.julia.nogueira.msmelhoria.dto.MelhoriaDto;
import br.julia.nogueira.msmelhoria.dto.VotoTeste;
import br.julia.nogueira.msmelhoria.entity.Melhoria;
import br.julia.nogueira.msmelhoria.repository.MelhoriaRepository;
import br.julia.nogueira.msmelhoria.service.MelhoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("melhoria")
public class MelhoriaController {
    @Autowired
    MelhoriaService service;

    @Autowired
    MelhoriaRepository repository;




    @PostMapping
    @Transactional
    public ResponseEntity guardarMelhorias(@RequestBody MelhoriaDto dados, UriComponentsBuilder buider){
        var propostas =  service.cadastrarProposta(dados);
        var uri  = buider.path("/melhoria/{id}").buildAndExpand(propostas.getId()).toUri();
        return ResponseEntity.created(uri).body(new MelhoriaDto(propostas));

    }

    @PutMapping("/votacao")
    @Transactional
    public ResponseEntity abrirVotacao(@RequestBody VotoTeste voto){
        var teste = repository.getReferenceById(voto.getIdMelhoria());
        teste.atualizarVotos(voto);
        return ResponseEntity.ok().build();

    }







}
