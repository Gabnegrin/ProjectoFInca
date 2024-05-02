package proj.finca.crea_tu_finca.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.finca.crea_tu_finca.dto.SolicitudDTO;
import proj.finca.crea_tu_finca.dto.SolicitudDTO2;
import proj.finca.crea_tu_finca.entidades.Solicitud;
import proj.finca.crea_tu_finca.servicio.SolicitudServicio;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/solicitud")
public class SolicitudControlador {

    private final SolicitudServicio solicitudServicio;

    @Autowired
    public SolicitudControlador(SolicitudServicio solicitudServicio) {
        this.solicitudServicio = solicitudServicio;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO2 get(@PathVariable Long id) {
        return solicitudServicio.get(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SolicitudDTO2> getAll() {
        return solicitudServicio.getAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO2 save(@RequestBody SolicitudDTO solicitudDTO) {
        return solicitudServicio.save(solicitudDTO);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO2 update(@RequestBody Solicitud solicitud) {
        return solicitudServicio.update(solicitud);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        solicitudServicio.delete(id);
    }
}
