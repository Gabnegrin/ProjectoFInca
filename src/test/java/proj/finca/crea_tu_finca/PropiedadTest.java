package proj.finca.crea_tu_finca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import proj.finca.crea_tu_finca.dto.PropiedadDTO;
import proj.finca.crea_tu_finca.servicio.PropiedadServicio;

@Transactional
@SpringBootTest
public class PropiedadTest {

    @Autowired
    private PropiedadServicio propiedadServicio;

    @Test
    void testGuardarActualizarEliminarPropiedad() {
        // Obtenemos la cantidad de propiedades antes de guardar una nueva
        List<PropiedadDTO> propiedadesAntes = propiedadServicio.getAll();
        int cantidadAntes = propiedadesAntes.size();

        // Creamos una nueva propiedad y la guardamos
        PropiedadDTO nuevaPropiedad = new PropiedadDTO();
        nuevaPropiedad.setNombre("Finca Los Pinos");
        nuevaPropiedad.setVisitas(0);
        nuevaPropiedad.setCalificacion(0);
        nuevaPropiedad.setValordia(100);
        nuevaPropiedad.setEliminado(false);

        PropiedadDTO propiedadGuardada = propiedadServicio.save(nuevaPropiedad);

        // Obtenemos la cantidad de propiedades después de guardar una nueva
        int cantidadDespues = propiedadServicio.getAll().size();

        // Verificamos que la cantidad de propiedades aumentó en 1 después de guardar una nueva
        assertEquals(cantidadAntes + 1, cantidadDespues);

        // Actualizamos la propiedad recién guardada
        propiedadGuardada.setNombre("Finca El Encanto");
        propiedadGuardada.setValordia(150);
        propiedadServicio.update(propiedadGuardada);

        // Obtenemos la propiedad actualizada
        PropiedadDTO propiedadActualizada = propiedadServicio.get(propiedadGuardada.getId());

        // Verificamos que la propiedad se haya actualizado correctamente
        assertEquals("Finca El Encanto", propiedadActualizada.getNombre());
        assertEquals(150, propiedadActualizada.getValordia());

        // Eliminamos la propiedad
        propiedadServicio.delete(propiedadActualizada.getId());

        // Verificamos que la propiedad se haya eliminado correctamente
        List<PropiedadDTO> propiedadesDespuesDeEliminar = propiedadServicio.getAll();
        assertEquals(cantidadAntes + 1, propiedadesDespuesDeEliminar.size());
    }
}
