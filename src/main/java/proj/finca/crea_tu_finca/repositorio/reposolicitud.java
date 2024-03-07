package proj.finca.crea_tu_finca.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.finca.crea_tu_finca.entidades.Solicitud;

@Repository
public interface reposolicitud extends CrudRepository<Solicitud, Long>{

}
