package com.nogueira.julia.msuser.controller;

import com.nogueira.julia.msuser.domain.dto.DadosFuncionario;
import com.nogueira.julia.msuser.domain.dto.DadosVotos;
import com.nogueira.julia.msuser.domain.repository.FuncionarioRepository;
import com.nogueira.julia.msuser.domain.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("employees")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Autowired
    private FuncionarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity inserirFuncionario(@RequestBody @Valid DadosFuncionario dados, UriComponentsBuilder uriBuilder) throws IllegalAccessException {
        var funcionarioCadastrado = service.cadastrarFuncionario(dados);
        var uri = uriBuilder.path("/employees/{id}").buildAndExpand(dados.id()).toUri();

        return ResponseEntity.created(uri).body(new DadosFuncionario(funcionarioCadastrado));
    }

     @GetMapping("/{id}")
    public ResponseEntity listar(@PathVariable Long id){
           var funcionario = repository.getReferenceById(id);
           return ResponseEntity.ok().body(new DadosFuncionario(funcionario));
     }

    @PutMapping("/votos")
    public void cadastrarVoto(@RequestBody DadosVotos votos) throws IllegalAccessException {
        service.evitaVotosRepetidos(votos);
        service.cadastrarVoto(votos);
    }




}
