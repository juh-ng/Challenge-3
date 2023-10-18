package br.julia.nogueira.msmelhoria.controller;

import br.julia.nogueira.msmelhoria.dto.DadosMelhoria;
import br.julia.nogueira.msmelhoria.dto.DadosVoto;
import br.julia.nogueira.msmelhoria.entity.Melhoria;
import br.julia.nogueira.msmelhoria.repository.MelhoriaRepository;
import br.julia.nogueira.msmelhoria.service.MelhoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("melhoria")
public class MelhoriaController {
    @Autowired
    MelhoriaService service;

    @Autowired
    MelhoriaRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity guardarMelhorias(@RequestBody DadosMelhoria dados, UriComponentsBuilder buider) {
        var propostas = service.cadastrarProposta(dados);
        var uri = buider.path("/melhoria/{id}").buildAndExpand(propostas.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosMelhoria(propostas));

    }

    @PutMapping("/votacao")
    @Transactional
    public ResponseEntity abrirVotacao(@RequestBody DadosVoto voto) {
        var dados = repository.getReferenceById(voto.getIdMelhoria());
        dados.atualizarVotos(voto);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/propostas")
    public ResponseEntity<Page<DadosMelhoria>> listarPropostas(@PageableDefault(size = 3, sort = {"id"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosMelhoria::new);
        return ResponseEntity.ok(page);

    }



}
