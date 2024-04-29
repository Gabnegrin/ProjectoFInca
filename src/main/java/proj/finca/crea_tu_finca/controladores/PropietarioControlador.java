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

import proj.finca.crea_tu_finca.dto.PropietarioDTO2;
import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.servicio.PropietarioServicio;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/propietario")
public class PropietarioControlador {

    private final PropietarioServicio propietarioServicio;

    @Autowired
    public PropietarioControlador(PropietarioServicio propietarioServicio) {
        this.propietarioServicio = propietarioServicio;
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropietarioDTO2 get(@PathVariable Long id) {
        return propietarioServicio.get(id);
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropietarioDTO2> getAll() {
        return propietarioServicio.getAll();
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropietarioDTO2 save(@RequestBody Propietario propietarioo) {
        return propietarioServicio.save(propietarioo);
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropietarioDTO2 update(@RequestBody Propietario propietarioo) {
        return propietarioServicio.update(propietarioo);
    }

    @CrossOrigin(origins = "http://10.43.101.3:4200")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        propietarioServicio.delete(id);
    }
}
