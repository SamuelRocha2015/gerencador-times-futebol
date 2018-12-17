package br.com.codenatio.desafio.reositories;

import br.com.codenatio.desafio.models.Jogador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JogadorRepository  extends CrudRepository<Jogador, Long> {
    @Query( value = "SELECT * FROM JOGADOR WHERE IS_CAPITAO = TRUE",
            nativeQuery = true)
    Optional<Jogador> findByCapitao();


    @Query( value = "SELECT * FROM JOGADOR WHERE  IS_CAPITAO = TRUE AND TIME_ID = :#{#idTime} ",
            nativeQuery = true)
    Optional<Jogador> findCapitaoByIdTime(@Param("idTime")Long idTime);


    @Query( value = "SELECT ID FROM JOGADOR WHERE TIME_ID = :#{#idTime} ",
            nativeQuery = true)
    Optional<List<Long>> findAllByIdTime(@Param("idTime")Long idTime);
}
