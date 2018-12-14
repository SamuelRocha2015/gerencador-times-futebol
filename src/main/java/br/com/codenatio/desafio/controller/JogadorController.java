package br.com.codenatio.desafio.controller;

import br.com.codenatio.desafio.models.Jogador;
import br.com.codenatio.desafio.service.MeuTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/jogador")
public class JogadorController {


    private final MeuTimeService service;

    @Autowired
    public JogadorController(MeuTimeService service) {
        this.service = service;
    }

    @PostMapping
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
}
