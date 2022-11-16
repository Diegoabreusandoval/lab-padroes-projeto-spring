package one.digitalinnovation.gof.Model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository<Endereco> extends CrudRepository<Endereco, String> {

}
}
