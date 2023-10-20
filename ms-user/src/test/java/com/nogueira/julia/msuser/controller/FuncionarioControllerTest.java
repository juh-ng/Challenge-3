package com.nogueira.julia.msuser.controller;

import com.nogueira.julia.msuser.domain.dto.DadosFuncionario;
import com.nogueira.julia.msuser.domain.entity.Funcionario;
import com.nogueira.julia.msuser.domain.service.FuncionarioService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosFuncionario> dadosFuncionarioJackson;

    @MockBean
    private FuncionarioService service;




    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão invalidas")
    void inserirFuncionario_cenario1() throws Exception{
        var response = mvc.perform(post("/employees"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")

    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DadosFuncionario(
                null,
                "236.958.200-63");

        when(service.cadastrarFuncionario(any())).thenReturn(new Funcionario(dadosCadastro));

        var response = mvc
                .perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosFuncionarioJackson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DadosFuncionario(
                null,
                dadosCadastro.cpf());
        var jsonEsperado = dadosFuncionarioJackson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }









}