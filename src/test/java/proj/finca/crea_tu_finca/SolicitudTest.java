package proj.finca.crea_tu_finca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import proj.finca.crea_tu_finca.dto.SolicitudDTO;
import proj.finca.crea_tu_finca.servicio.SolicitudServicio;

@Transactional
@SpringBootTest
public class SolicitudTest {

    @Autowired
    private SolicitudServicio solicitudServicio;

    @Test
    void testGuardarActualizarEliminarSolicitud() {
        // Obtenemos la cantidad de solicitudes antes de guardar una nueva
        List<SolicitudDTO> solicitudesAntes = solicitudServicio.getAll();
        int cantidadAntes = solicitudesAntes.size();

        // Creamos una nueva solicitud y la guardamos
        SolicitudDTO nuevaSolicitud = new SolicitudDTO();
        nuevaSolicitud.setC_cliente(5);
        nuevaSolicitud.setC_propietario(4);
        nuevaSolicitud.setC_propiedad(3);
        nuevaSolicitud.setPreciot(100);
        nuevaSolicitud.setEntrada(LocalDate.now());
        nuevaSolicitud.setSalida(LocalDate.now().plusDays(5));
        nuevaSolicitud.setEstado(1);
        nuevaSolicitud.setEliminado(false);
        // Aquí puedes establecer la propiedad2 si lo deseas
        // nuevaSolicitud.setPropiedad2(...);

        SolicitudDTO solicitudGuardada = solicitudServicio.save(nuevaSolicitud);

        // Obtenemos la cantidad de solicitudes después de guardar una nueva
        int cantidadDespues = solicitudServicio.getAll().size();

        // Verificamos que la cantidad de solicitudes aumentó en 1 después de guardar una nueva
        assertEquals(cantidadAntes + 1, cantidadDespues);

        // Actualizamos la solicitud recién guardada
        solicitudGuardada.setC_cliente(cantidadDespues);
        solicitudGuardada.setEstado(2);
        solicitudServicio.update(solicitudGuardada);

        // Obtenemos la solicitud actualizada
        SolicitudDTO solicitudActualizada = solicitudServicio.get(solicitudGuardada.getId());

        // Verificamos que la solicitud se haya actualizado correctamente
        assertEquals(cantidadDespues, solicitudActualizada.getC_cliente());
        assertEquals(2, solicitudActualizada.getEstado());

        // Eliminamos la solicitud
        solicitudServicio.delete(solicitudActualizada.getId());

        // Verificamos que la solicitud se haya eliminado correctamente
        List<SolicitudDTO> solicitudesDespuesDeEliminar = solicitudServicio.getAll();
        assertEquals(cantidadAntes + 1, solicitudesDespuesDeEliminar.size());
    }
}

