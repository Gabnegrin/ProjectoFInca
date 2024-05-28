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

import proj.finca.crea_tu_finca.entidades.Cliente;
import proj.finca.crea_tu_finca.dto.ClienteDTO2;
import proj.finca.crea_tu_finca.servicio.ClienteServicio;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/cliente")
public class ClienteControlador {

    ClienteServicio clienteServicio;

    @Autowired
    ClienteControlador(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO2 get(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String authorizationHeader){
        return clienteServicio.get(id, authorizationHeader);
    }
    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClienteDTO2> get(@RequestHeader(value = "Authorization", required = false) String authorizationHeader){
        return clienteServicio.get(authorizationHeader);
    }
    @CrossOrigin(origins = "*")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO2 save(@RequestBody Cliente clientee){
        return clienteServicio.save(clientee);
    }
    @CrossOrigin(origins = "*")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO2 update(@RequestBody Cliente clientee, @RequestHeader(value = "Authorization", required = false) String authorizationHeader){
        return clienteServicio.update(clientee, authorizationHeader);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String authorizationHeader){
        clienteServicio.delete(id, authorizationHeader);
    }
}
