package proj.finca.crea_tu_finca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.repositorio.repopropietario;

//@RequestMapping(value = "/api/javeriana/auth/application")
@RestController
public class controladorpropietario {
    @Autowired
    private repopropietario repositorio;

    @CrossOrigin
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Propietario> get() throws Exception {
        return repositorio.findAll();
    }
    @SuppressWarnings("null")
    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Propietario get(@PathVariable Long id) throws Exception {
        return repositorio.findById(id).orElse(null);
    }
    @SuppressWarnings("null")
    @CrossOrigin
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Propietario save(@RequestBody Propietario propietario) throws Exception {
        return repositorio.save(propietario);
    }

    @SuppressWarnings("null")
    @CrossOrigin
    @PutMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Propietario update( @RequestBody Propietario propietario) throws Exception {
        return repositorio.save(propietario);
    }
    @SuppressWarnings("null")
    @CrossOrigin
    @DeleteMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) throws Exception {
        repositorio.deleteById(id);
    }
}
