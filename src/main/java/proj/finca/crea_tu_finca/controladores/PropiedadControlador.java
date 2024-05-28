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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.finca.crea_tu_finca.dto.PropiedadDTO;
import proj.finca.crea_tu_finca.dto.PropiedadDTO2;
import proj.finca.crea_tu_finca.entidades.Propiedad;
import proj.finca.crea_tu_finca.servicio.PropiedadServicio;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/propiedad")
public class PropiedadControlador {

    private final PropiedadServicio propiedadServicio;

    @Autowired
    public PropiedadControlador(PropiedadServicio propiedadServicio) {
        this.propiedadServicio = propiedadServicio;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO2 get(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        return propiedadServicio.get(id, authorizationHeader);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO2> getAll(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        return propiedadServicio.getAll(authorizationHeader);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO2 save(@RequestBody PropiedadDTO propiedadDTO, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        return propiedadServicio.save(propiedadDTO, authorizationHeader);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO2 update(@RequestBody Propiedad propiedad, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        return propiedadServicio.update(propiedad, authorizationHeader);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        propiedadServicio.delete(id, authorizationHeader);
    }
}
