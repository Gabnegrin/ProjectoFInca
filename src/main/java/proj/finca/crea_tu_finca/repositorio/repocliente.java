package proj.finca.crea_tu_finca.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.finca.crea_tu_finca.entidades.Cliente;

@Repository
public interface repocliente extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByUsuarioAndContrasena(String usuario, String contrasena);
    Optional<Cliente> findByUsuario(String usuario);
}
