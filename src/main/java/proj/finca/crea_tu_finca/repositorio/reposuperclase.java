package proj.finca.crea_tu_finca.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.finca.crea_tu_finca.entidades.superclase;

@Repository
public interface reposuperclase extends CrudRepository<superclase, Long>{

}