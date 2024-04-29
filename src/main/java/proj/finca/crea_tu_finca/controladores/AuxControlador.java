package proj.finca.crea_tu_finca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.MediaType;
import proj.finca.crea_tu_finca.dto.ClienteDTO2;
import proj.finca.crea_tu_finca.dto.PropiedadDTO2;
import proj.finca.crea_tu_finca.dto.PropietarioDTO2;
import proj.finca.crea_tu_finca.dto.SolicitudDTO2;
import proj.finca.crea_tu_finca.entidades.Cliente;
import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.servicio.AuxServicio;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/aux")
public class AuxControlador {

    private final AuxServicio auxServicio;

    @Autowired
    public AuxControlador(AuxServicio auxServicio) {
        this.auxServicio = auxServicio;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/cliente/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO2 autenticarCliente(@RequestBody Cliente cliente) {
        return auxServicio.ClientegetByUsernameAndPassword(cliente);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/propietario/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropietarioDTO2 autenticarPropietario(@RequestBody Propietario propietario) {
        return auxServicio.PropietariogetByUsernameAndPassword(propietario);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/solicitud/propietario/{propietarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SolicitudDTO2> obtenerSolicitudesPorPropietario(@PathVariable Long propietarioId) {
        return auxServicio.getByPropietarioId(propietarioId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/solicitud/cliente/{clienteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SolicitudDTO2> obtenerSolicitudesPorCliente(@PathVariable Long clienteId) {
        return auxServicio.getByClienteId(clienteId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/propiedad/propietario/{propietarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO2> obtenerPropiedadesPorPropietario(@PathVariable Long propietarioId) {
        return auxServicio.propiedadesgetByPropietarioId(propietarioId);
    }
}

