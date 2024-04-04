package proj.finca.crea_tu_finca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import proj.finca.crea_tu_finca.dto.ClienteDTO;
import proj.finca.crea_tu_finca.servicio.ClienteServicio;

@Transactional
@SpringBootTest
public class ClienteTest {


    private ClienteServicio clienteServicio;

    @Autowired
    ClienteTest(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
    }

    @Test
    void ClienteServicioTest(){
        System.out.println("----------------LLEGUE AQUI");
        // Obtenemos la cantidad de clientes antes de guardar uno nuevo
        List<ClienteDTO> clientes = clienteServicio.get();
        int cantidadAntes = clientes.size();

        System.out.println("----------------LLEGUE AQUI POR 2");

        // Creamos un nuevo cliente y lo guardamos
        ClienteDTO nuevoClienteDTO = new ClienteDTO();
        nuevoClienteDTO.setNombre("Juan");
        nuevoClienteDTO.setApellido("Pérez");
        nuevoClienteDTO.setCorreo("juan.perez@example.com");
        nuevoClienteDTO.setEdad(0);
        nuevoClienteDTO.setCalificacion(0);
        nuevoClienteDTO.setEliminado(false);

        ClienteDTO clienteGuardadoDTO = clienteServicio.save(nuevoClienteDTO);

        // Obtenemos la cantidad de clientes después de guardar uno nuevo
        int cantidadDespues = clienteServicio.get().size();

        // Verificamos que la cantidad de clientes aumentó en 1 después de guardar uno nuevo
        assertEquals(cantidadAntes + 1, cantidadDespues);
        System.out.println("----------");

        // Actualizamos el nombre del cliente recién guardado
        clienteGuardadoDTO.setNombre("Juan Andrés");
        clienteServicio.update(clienteGuardadoDTO);

        // Obtenemos el cliente actualizado
        ClienteDTO clienteActualizadoDTO = clienteServicio.get(clienteGuardadoDTO.getId());

        // Verificamos que el nombre del cliente se actualizó correctamente
        assertEquals("Juan Andrés", clienteActualizadoDTO.getNombre());
        System.out.println("-----------");
        // Eliminamos el cliente
        clienteServicio.delete(clienteActualizadoDTO.getId());

        // Verificamos que el cliente se haya eliminado correctamente
        List<ClienteDTO> clientesDespuesDeEliminar = clienteServicio.get();
        assertEquals(cantidadAntes + 1, clientesDespuesDeEliminar.size());

        // Creamos otro cliente y lo guardamos
        ClienteDTO otroClienteDTO = new ClienteDTO();
        otroClienteDTO.setNombre("Luis");
        otroClienteDTO.setApellido("Martínez");
        otroClienteDTO.setCorreo("luis.martinez@example.com");
        otroClienteDTO.setEdad(25);
        otroClienteDTO.setCalificacion(0);
        otroClienteDTO.setEliminado(false);

        ClienteDTO otroClienteGuardadoDTO = clienteServicio.save(otroClienteDTO);

        // Verificamos que el cliente se haya guardado correctamente
        assertTrue(otroClienteGuardadoDTO.getId() > 0);
        assertEquals("Luis", otroClienteGuardadoDTO.getNombre());
        assertEquals("Martínez", otroClienteGuardadoDTO.getApellido());
        assertEquals("luis.martinez@example.com", otroClienteGuardadoDTO.getCorreo());
        assertEquals(25, otroClienteGuardadoDTO.getEdad());
        assertEquals(0, otroClienteGuardadoDTO.getCalificacion());
        assertFalse(otroClienteGuardadoDTO.isEliminado());

        System.out.println("-----------");
    }
}
