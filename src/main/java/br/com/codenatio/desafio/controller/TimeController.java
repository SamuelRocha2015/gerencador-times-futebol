package br.com.codenatio.desafio.controller;

import br.com.codenatio.desafio.models.Time;
import br.com.codenatio.desafio.service.MeuTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/time")
public class TimeController {

    private final MeuTimeService service;

    @Autowired
    public TimeController(MeuTimeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody Time time){
        service.incluirTime(time.getId(), time.getNome(), time.getDataCriacao(),
                time.getCorUniformePrincipal(), time.getCorUniformeSecundario());
    }

}
