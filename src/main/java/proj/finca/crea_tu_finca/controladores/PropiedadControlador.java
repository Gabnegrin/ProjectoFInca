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

import proj.finca.crea_tu_finca.dto.PropiedadDTO;
import proj.finca.crea_tu_finca.dto.PropiedadDTO2;
import proj.finca.crea_tu_finca.servicio.PropiedadServicio;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/propiedad")
public class PropiedadControlador {

    private final PropiedadServicio propiedadServicio;

    @Autowired
    public PropiedadControlador(PropiedadServicio propiedadServicio) {
        this.propiedadServicio = propiedadServicio;
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO2 get(@PathVariable Long id) {
        return propiedadServicio.get(id);
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO2> getAll() {
        return propiedadServicio.getAll();
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO2 save(@RequestBody PropiedadDTO propiedadDTO) {
        return propiedadServicio.save(propiedadDTO);
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO2 update(@RequestBody PropiedadDTO propiedadDTO) {
        return propiedadServicio.update(propiedadDTO);
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        propiedadServicio.delete(id);
    }
}
