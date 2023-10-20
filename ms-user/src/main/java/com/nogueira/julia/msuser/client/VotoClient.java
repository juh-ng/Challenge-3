package com.nogueira.julia.msuser.client;

import com.nogueira.julia.msuser.domain.dto.DadosVotos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ms-melhoria")
public interface VotoClient {

    @RequestMapping(method = RequestMethod.PUT, value = "melhoria/votacao")
    public void registraVotacao(DadosVotos votos);
}
