package proj.finca.crea_tu_finca.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.finca.crea_tu_finca.entidades.Propietario;

@Repository
public interface repopropietario extends CrudRepository<Propietario, Long> {
    Optional<Propietario> findByUsuarioAndContrasena(String usuario, String contrasena);
}
