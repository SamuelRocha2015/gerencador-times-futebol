package br.com.codenatio.desafio.controller;

import br.com.codenatio.desafio.models.Jogador;
import br.com.codenatio.desafio.models.Time;
import br.com.codenatio.desafio.service.MeuTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/meu-time")
public class MeuTimeController {


    private final MeuTimeService service;

    @Autowired
    public MeuTimeController(MeuTimeService service) {
        this.service = service;
    }

    @PostMapping("/jogador")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody Jogador jogador) {
        service.incluirJogador(jogador.getId(), jogador.getTime().getId(), jogador.getNome(), jogador.getDataNascimento(),
                jogador.getNivelHabilidade(), jogador.getSalario());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCapitao(@PathVariable String id){
        service.definirCapitao(Long.valueOf(id));
    }

    @PostMapping("/time")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody Time time){
        service.incluirTime(time.getId(), time.getNome(), time.getDataCriacao(),
                time.getCorUniformePrincipal(), time.getCorUniformeSecundario());
    }

    @GetMapping("/{idTime}")
    @ResponseStatus(HttpStatus.OK)
    public long buscarCapitaoDoTime(@PathVariable String idTime){
        return service.buscarCapitaoDoTime(Long.valueOf(idTime));
    }

}
