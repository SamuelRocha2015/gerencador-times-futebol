package br.com.codenatio.desafio.reositories;

import br.com.codenatio.desafio.models.Jogador;
import org.springframework.data.repository.CrudRepository;

public interface JogadorRepository  extends CrudRepository<Jogador, Long> {
}
