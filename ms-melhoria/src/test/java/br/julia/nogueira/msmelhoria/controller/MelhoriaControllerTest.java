package br.julia.nogueira.msmelhoria.controller;

import br.julia.nogueira.msmelhoria.dto.DadosMelhoria;
import br.julia.nogueira.msmelhoria.entity.Melhoria;
import br.julia.nogueira.msmelhoria.service.MelhoriaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MelhoriaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester <DadosMelhoria> dadosPropostasTeste;

    @MockBean
    MelhoriaService service;


    @Test
    @DisplayName("Retorna codigo HTTP 400 quando informacoes estao invalidas")
    void guardarMelhorias() throws Exception {
       var response =  mvc.perform(post("/melhoria")).andReturn().getResponse();

       assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }


    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")

    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DadosMelhoria(
                null,
                "teste-melhoria",0);

        when(service.cadastrarProposta(any())).thenReturn(new Melhoria(dadosCadastro));
        var melhoriaRetornada = new Melhoria(dadosCadastro);

        var response = mvc
                .perform(post("/melhoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosPropostasTeste.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DadosMelhoria(
                melhoriaRetornada.getId(),
                melhoriaRetornada.getTipoMelhoria(),
                melhoriaRetornada.getQtdVoto());
        var jsonEsperado = dadosPropostasTeste.write(dadosDetalhamento).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}