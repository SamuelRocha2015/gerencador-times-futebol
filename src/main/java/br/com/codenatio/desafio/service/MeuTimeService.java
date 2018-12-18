package br.com.codenatio.desafio.service;

import br.com.codenatio.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenatio.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenatio.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenatio.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenatio.desafio.models.Jogador;
import br.com.codenatio.desafio.models.Time;
import br.com.codenatio.desafio.reositories.JogadorRepository;
import br.com.codenatio.desafio.reositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeuTimeService implements MeuTimeInterface {

    private final TimeRepository timeRepository;
    private final JogadorRepository jogadorRepository;

    @Autowired
    public MeuTimeService(TimeRepository timeRepository, JogadorRepository jogadorRepository) {
        this.timeRepository = timeRepository;
        this.jogadorRepository = jogadorRepository;
    }


    @Override
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

        Optional<Time> byId = timeRepository.findById(id);
        if(byId.isPresent()){
            throw new IdentificadorUtilizadoException();
        }

        Time novo = new Time();
        novo.setId(id);
        novo.setNome(nome);
        novo.setDataCriacao(dataCriacao);
        novo.setCorUniformePrincipal(corUniformePrincipal);
        novo.setCorUniformeSecundario(corUniformeSecundario);

        timeRepository.save(novo);

    }

    @Override
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

        Optional<Jogador> byId = jogadorRepository.findById(id);
        if(byId.isPresent()){
            throw new IdentificadorUtilizadoException();
        }

        Optional<Time> timeById = timeRepository.findById(idTime);
        if(!timeById.isPresent()){
            throw new TimeNaoEncontradoException();
        }

        Jogador novo = new Jogador();
        novo.setId(id);
        novo.setNome(nome);
        novo.setDataNascimento(dataNascimento);
        novo.setNivelHabilidade(nivelHabilidade);
        novo.setSalario(salario);
        novo.setTime(timeById.get());

        jogadorRepository.save(novo);

    }

    @Override
    @Transactional
    public void definirCapitao(Long idJogador) {

        Optional<Jogador> byId = jogadorRepository.findById(idJogador);
        if(!byId.isPresent()){
            throw new JogadorNaoEncontradoException();
        }

        Optional<Jogador> antigoCapitao = jogadorRepository.findByCapitao();
         if(antigoCapitao .isPresent()){
            Jogador antigoCap = antigoCapitao.get();
            antigoCap.setCapitao(false);
            jogadorRepository.save(antigoCap);
        }


        Jogador jogador = byId.get();
        jogador.setCapitao(true);

        jogadorRepository.save(jogador);

    }

    @Override
    @Transactional
    public Long buscarCapitaoDoTime(Long idTime) {

        Optional<Time> timeById = timeRepository.findById(idTime);
        if(!timeById.isPresent()){
            throw new TimeNaoEncontradoException();
        }

        Optional<Jogador> capitaoTime = jogadorRepository.findCapitaoByIdTime(idTime);
        if(!capitaoTime.isPresent()){
            throw new CapitaoNaoInformadoException();
        }

        return capitaoTime.get().getId();
    }

    @Override
    public String buscarNomeJogador(Long idJogador) {

        Optional<Jogador> jogadorById = jogadorRepository.findById(idJogador);
        if(!jogadorById.isPresent()){
            throw new JogadorNaoEncontradoException();
        }

        return jogadorById.get().getNome();
    }

    @Override
    public String buscarNomeTime(Long idTime) {

        Optional<Time> timeById = timeRepository.findById(idTime);
        if(!timeById.isPresent()){
            throw new TimeNaoEncontradoException();
        }

        return timeById.get().getNome();
    }

    @Override
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        Optional<List<Long>> allByIdTime = jogadorRepository.findAllByIdTime(idTime);
        if(!allByIdTime.isPresent()) {
            throw new JogadorNaoEncontradoException();
        }

        return allByIdTime.get();
    }

    @Override
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Optional<Time> timeById = timeRepository.findById(idTime);
        if(!timeById.isPresent()){
            throw new TimeNaoEncontradoException();
        }

        Optional<Jogador> jogadorMaiorSalarioByIdTime = jogadorRepository.findJogadorMaiorSalarioByIdTime(idTime);
        if(!jogadorMaiorSalarioByIdTime.isPresent()){
            throw new JogadorNaoEncontradoException();
        }

        return jogadorMaiorSalarioByIdTime.get().getId();
    }

    @Override
    public Long buscarJogadorMaisVelho(Long idTime) {

        Optional<Time> timeById = timeRepository.findById(idTime);
        if (!timeById.isPresent()) {
            throw new TimeNaoEncontradoException();
        }

        Optional<Jogador> jogadorMaisVelho = jogadorRepository.findJogadrMaisVelho(idTime);

        if(!jogadorMaisVelho.isPresent()){
            throw new JogadorNaoEncontradoException();
        }

        return jogadorMaisVelho.get().getId();
    }


    @Override
    public List<Long> buscarTimes() {
        List<Long> ids = new ArrayList<>();
        Iterable<Time> all = timeRepository.findAll();
        all.forEach(a->ids.add(a.getId()));
        return ids;
    }


    @Override
    @Transactional
    public Long buscarJogadorMaiorSalario(Long idTime) {
        Optional<Time> timeById = timeRepository.findById(idTime);
        if (!timeById.isPresent()) {
            throw new TimeNaoEncontradoException();
        }

        Optional<Jogador> jogadorMaiorSalario = jogadorRepository.findByMaxSalarioAndTimeId(idTime);

        if(!jogadorMaiorSalario.isPresent()){
            throw new JogadorNaoEncontradoException();
        }

        return jogadorMaiorSalario.get().getId();
    }

    @Override
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {

        Optional<Jogador> byId = jogadorRepository.findById(idJogador);
        if(!byId.isPresent()){
            throw new JogadorNaoEncontradoException();
        }

        return byId.get().getSalario();
    }

    @Override
    public List<Long> buscarTopJogadores(Integer top) {
        return null;
    }
}
