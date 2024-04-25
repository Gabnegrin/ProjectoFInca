package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.finca.crea_tu_finca.entidades.Cliente;
import proj.finca.crea_tu_finca.dto.ClienteDTO2;
import proj.finca.crea_tu_finca.repositorio.repocliente;

@Service
public class ClienteServicio {

    repocliente clienterepositorio;
    ModelMapper modelMapper;

    @Autowired
    ClienteServicio(repocliente clienterepositorio, ModelMapper modelMapper){
        this.clienterepositorio = clienterepositorio;
        this.modelMapper = modelMapper;
    }

    @SuppressWarnings("null")
    public ClienteDTO2 get(Long id){
        Optional<Cliente>clienteOpt = clienterepositorio.findById(id);
        ClienteDTO2 clienteDTO2 = null;
        if(clienteOpt.isPresent()){
            Cliente client = clienteOpt.get();
            clienteDTO2 = modelMapper.map(client, ClienteDTO2.class);
        }
        return clienteDTO2;
    }

    public List<ClienteDTO2> get(){
        List<Cliente> clientes = (List<Cliente>) clienterepositorio.findAll();
        List<ClienteDTO2> clienteDTO2s = clientes.stream()
                                                        .map(cliente -> modelMapper.map(cliente, ClienteDTO2.class))
                                                        .collect(Collectors.toList());
        return clienteDTO2s;
    }
    
    @SuppressWarnings("null")
    public ClienteDTO2 save(Cliente clientee){
        Cliente cliente = clientee;
        cliente = clienterepositorio.save(cliente);
        ClienteDTO2 clienteDTO = modelMapper.map(cliente, ClienteDTO2.class);
        return clienteDTO;
    }

    @SuppressWarnings("null")
    public ClienteDTO2 update(Cliente clientee){
        Cliente cliente = clienterepositorio.save(clientee);
        ClienteDTO2 clienteDTO = modelMapper.map(cliente, ClienteDTO2.class);
        return clienteDTO;
    }
    @SuppressWarnings("null")
    public void delete(Long id){
    Optional<Cliente> clienteOpt = clienterepositorio.findById(id);
    clienteOpt.ifPresent(cliente -> {
        cliente.setEliminado(true);
        clienterepositorio.save(cliente);
    });
}

}
