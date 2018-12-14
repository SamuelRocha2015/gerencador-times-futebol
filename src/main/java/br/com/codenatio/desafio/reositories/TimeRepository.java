package br.com.codenatio.desafio.reositories;

import br.com.codenatio.desafio.models.Time;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends CrudRepository<Time, Long>  {

}
