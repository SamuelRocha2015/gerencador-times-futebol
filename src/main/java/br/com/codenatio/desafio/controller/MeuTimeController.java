package br.com.codenatio.desafio.controller;

import br.com.codenatio.desafio.models.Jogador;
import br.com.codenatio.desafio.models.Time;
import br.com.codenatio.desafio.service.MeuTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

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

    @GetMapping("/jogador/{idJogador}")
    @ResponseStatus(HttpStatus.OK)
    public String buscarNomeJogador(@PathVariable String idJogador){
        return service.buscarNomeJogador(Long.valueOf(idJogador));
    }

    @GetMapping("/time/{idTime}")
    @ResponseStatus(HttpStatus.OK)
    public String buscarNomeTime(@PathVariable String idTime){
        return service.buscarNomeTime (Long.valueOf(idTime));
    }


    @GetMapping("/jogadores/{idTime}")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> buscarJogadoresDoTime(@PathVariable String idTime){
        return service.buscarJogadoresDoTime (Long.valueOf(idTime));
    }

    @GetMapping("/melhor-jogador/{idTime}")
    @ResponseStatus(HttpStatus.OK)
    public Long buscarMelhorJogadorDoTime(@PathVariable String idTime){
        return service.buscarMelhorJogadorDoTime(Long.valueOf(idTime));
    }

    @GetMapping("/jogador-mais-velho/{idTime}")
    @ResponseStatus(HttpStatus.OK)
    public Long buscarJogadorMaisVelho(@PathVariable String idTime){
        return service.buscarJogadorMaisVelho(Long.valueOf(idTime));
    }

    @GetMapping("/times")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> buscarTimes(){
        return service.buscarTimes();
    }

    @GetMapping("/jogador-maior-salario/{idTime}")
    @ResponseStatus(HttpStatus.OK)
    public Long buscarJogadorMaiorSalario(@PathVariable String idTime){
        return service.buscarJogadorMaiorSalario(Long.valueOf(idTime));
    }

    @GetMapping("/busca-salario-jogador/{idJogador}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal buscarSalarioDoJogador(@PathVariable String idJogador){
        return service.buscarSalarioDoJogador(Long.valueOf(idJogador));
    }

}
