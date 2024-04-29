package proj.finca.crea_tu_finca.repositorio;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.finca.crea_tu_finca.entidades.Propiedad;
import proj.finca.crea_tu_finca.entidades.Solicitud;

@Repository
public interface reposolicitud extends CrudRepository<Solicitud, Long>{
    List<Solicitud> findByPropiedad2(Propiedad propiedad);
    List<Solicitud> findByClienteId(Long clienteId);
}
