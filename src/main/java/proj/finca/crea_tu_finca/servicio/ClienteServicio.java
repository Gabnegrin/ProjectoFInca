package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.finca.crea_tu_finca.entidades.Cliente;
import proj.finca.crea_tu_finca.dto.ClienteDTO;
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
    public ClienteDTO get(Long id){
        Optional<Cliente>clienteOpt = clienterepositorio.findById(id);
        ClienteDTO clienteDTO = null;
        if(clienteOpt.isPresent()){
            Cliente client = clienteOpt.get();
            clienteDTO = modelMapper.map(client, ClienteDTO.class);
        }
        return clienteDTO;
    }

    public List<ClienteDTO> get(){
        List<Cliente> clientes = (List<Cliente>) clienterepositorio.findAll();
        List<ClienteDTO> clienteDTOs = clientes.stream()
                                                        .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                                                        .collect(Collectors.toList());
        return clienteDTOs;
    }
    
    @SuppressWarnings("null")
    public ClienteDTO save(ClienteDTO clienteDTO){
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente = clienterepositorio.save(cliente);
        clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        return clienteDTO;
    }

    @SuppressWarnings("null")
    public ClienteDTO update(ClienteDTO clienteDTO){
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente = clienterepositorio.save(cliente);
        clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        return clienteDTO;
    }
    @SuppressWarnings("null")
    public void delete(Long id){
        Optional<Cliente> clienteOpt = clienterepositorio.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setEliminado(true);
            clienterepositorio.save(cliente);
        }
    }
}
