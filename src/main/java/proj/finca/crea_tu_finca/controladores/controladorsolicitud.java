package proj.finca.crea_tu_finca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import proj.finca.crea_tu_finca.entidades.Solicitud;
import proj.finca.crea_tu_finca.repositorio.reposolicitud;

@RequestMapping(value = "/api/javeriana/grupo25/solicitud")
@RestController
public class controladorsolicitud {
    @Autowired
    private reposolicitud repositorio;

    @CrossOrigin
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Solicitud> get() throws Exception {
        return repositorio.findAll();
    }
    @SuppressWarnings("null")
    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Solicitud get(@PathVariable Long id) throws Exception {
        return repositorio.findById(id).orElse(null);
    }
    @SuppressWarnings("null")
    @CrossOrigin
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Solicitud save(@RequestBody Solicitud solicitud) throws Exception {
        return repositorio.save(solicitud);
    }

    @SuppressWarnings("null")
    @CrossOrigin
    @PutMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public Solicitud update( @RequestBody Solicitud solicitud ) throws Exception {
        return repositorio.save(solicitud);
    }
    @SuppressWarnings("null")
    @CrossOrigin
    @DeleteMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) throws Exception {
        repositorio.deleteById(id);
    }
}
