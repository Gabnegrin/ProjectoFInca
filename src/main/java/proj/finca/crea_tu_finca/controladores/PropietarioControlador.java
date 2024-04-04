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

import proj.finca.crea_tu_finca.dto.PropietarioDTO;
import proj.finca.crea_tu_finca.servicio.PropietarioServicio;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/propietario")
public class PropietarioControlador {

    private final PropietarioServicio propietarioServicio;

    @Autowired
    public PropietarioControlador(PropietarioServicio propietarioServicio) {
        this.propietarioServicio = propietarioServicio;
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropietarioDTO get(@PathVariable Long id) {
        return propietarioServicio.get(id);
    }

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropietarioDTO> getAll() {
        return propietarioServicio.getAll();
    }

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropietarioDTO save(@RequestBody PropietarioDTO propietarioDTO) {
        return propietarioServicio.save(propietarioDTO);
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropietarioDTO update(@RequestBody PropietarioDTO propietarioDTO) {
        return propietarioServicio.update(propietarioDTO);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        propietarioServicio.delete(id);
    }
}
