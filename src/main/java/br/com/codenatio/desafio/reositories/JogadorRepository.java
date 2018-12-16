package br.com.codenatio.desafio.reositories;

import br.com.codenatio.desafio.models.Jogador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JogadorRepository  extends CrudRepository<Jogador, Long> {
    @Query( value = "SELECT * FROM JOGADOR WHERE IS_CAPITAO = TRUE",
            nativeQuery = true)
    Optional<Jogador> findByCapitao();
}
