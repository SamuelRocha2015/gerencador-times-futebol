package br.com.codenatio.desafio.service;

import br.com.codenatio.desafio.reositories.JogadorRepository;
import br.com.codenatio.desafio.reositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    }

    @Override
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

    }

    @Override
    public void definirCapitao(Long idJogador) {

    }

    @Override
    public Long buscarCapitaoDoTime(Long idTime) {
        return null;
    }

    @Override
    public String buscarNomeJogador(Long idJogador) {
        return null;
    }

    @Override
    public String buscarNomeTime(Long idTime) {
        return null;
    }

    @Override
    public Long buscarJogadorMaiorSalario(Long idTime) {
        return null;
    }

    @Override
    public BigDecimal buscarSalarioDoJogaodor(Long idJogador) {
        return null;
    }

    @Override
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        return null;
    }

    @Override
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        return null;
    }

    @Override
    public Long buscarJogadorMaisVelho(Long idTime) {
        return null;
    }

    @Override
    public List<Long> buscarTimes() {
        return null;
    }

    @Override
    public List<Long> buscarTopJogadores(Integer top) {
        return null;
    }
}
