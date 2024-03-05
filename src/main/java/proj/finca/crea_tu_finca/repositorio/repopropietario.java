package proj.finca.crea_tu_finca.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.finca.crea_tu_finca.entidades.*;

@Repository
public interface repopropietario extends CrudRepository<Propietario, Long>{

}
