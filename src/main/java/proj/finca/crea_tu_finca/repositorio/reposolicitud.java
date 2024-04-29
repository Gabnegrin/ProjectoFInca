package proj.finca.crea_tu_finca.repositorio;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.finca.crea_tu_finca.entidades.Cliente;
import proj.finca.crea_tu_finca.entidades.Solicitud;

@Repository
public interface reposolicitud extends CrudRepository<Solicitud, Long>{
    List<Solicitud> findByPropietarioId(Long propietarioId);
    List<Solicitud> findByClienteId(Long clienteId);
}
