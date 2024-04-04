package proj.finca.crea_tu_finca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import proj.finca.crea_tu_finca.dto.PropietarioDTO;
import proj.finca.crea_tu_finca.servicio.PropietarioServicio;

@Transactional
@SpringBootTest
public class PropietarioTest {

    @Autowired
    private PropietarioServicio propietarioServicio;

    @Test
    void testGuardarActualizarEliminarPropietario() {
        // Obtenemos la cantidad de propietarios antes de guardar uno nuevo
        List<PropietarioDTO> propietariosAntes = propietarioServicio.getAll();
        int cantidadAntes = propietariosAntes.size();

        // Creamos un nuevo propietario y lo guardamos
        PropietarioDTO nuevoPropietario = new PropietarioDTO();
        nuevoPropietario.setNombre("Juan");
        nuevoPropietario.setApellido("Pérez");
        nuevoPropietario.setCorreo("juan.perez@example.com");
        nuevoPropietario.setUsuario("juanperez");
        nuevoPropietario.setEdad(30);
        nuevoPropietario.setCalificacion(0);
        nuevoPropietario.setEliminado(false);

        PropietarioDTO propietarioGuardado = propietarioServicio.save(nuevoPropietario);

        // Obtenemos la cantidad de propietarios después de guardar uno nuevo
        int cantidadDespues = propietarioServicio.getAll().size();

        // Verificamos que la cantidad de propietarios aumentó en 1 después de guardar uno nuevo
        assertEquals(cantidadAntes + 1, cantidadDespues);

        // Actualizamos el propietario recién guardado
        propietarioGuardado.setNombre("Pedro");
        propietarioGuardado.setEdad(35);
        propietarioServicio.update(propietarioGuardado);

        // Obtenemos el propietario actualizado
        PropietarioDTO propietarioActualizado = propietarioServicio.get(propietarioGuardado.getId());

        // Verificamos que el propietario se haya actualizado correctamente
        assertEquals("Pedro", propietarioActualizado.getNombre());
        assertEquals(35, propietarioActualizado.getEdad());

        // Eliminamos el propietario
        propietarioServicio.delete(propietarioActualizado.getId());

        // Verificamos que el propietario se haya eliminado correctamente
        List<PropietarioDTO> propietariosDespuesDeEliminar = propietarioServicio.getAll();
        assertEquals(cantidadAntes + 1, propietariosDespuesDeEliminar.size());
    }
}

