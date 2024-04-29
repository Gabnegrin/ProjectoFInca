package proj.finca.crea_tu_finca.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.finca.crea_tu_finca.entidades.Propiedad;

@Repository
public interface repopropiedad extends CrudRepository<Propiedad, Long> {
    List<Propiedad> findByPropietarioId(Long propietarioId);
}
