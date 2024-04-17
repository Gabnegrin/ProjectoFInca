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

import proj.finca.crea_tu_finca.dto.ClienteDTO;
import proj.finca.crea_tu_finca.servicio.ClienteServicio;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/cliente")
public class ClienteControlador {

    ClienteServicio clienteServicio;

    @Autowired
    ClienteControlador(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO get(@PathVariable Long id){
        return clienteServicio.get(id);
    }
    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClienteDTO> get(){
        return clienteServicio.get();
    }
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO save(@RequestBody ClienteDTO clienteDTO){
        return clienteServicio.save(clienteDTO);
    }
    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO update(@RequestBody ClienteDTO clienteDTO){
        return clienteServicio.update(clienteDTO);
    }
    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        clienteServicio.delete(id);
    }
}
