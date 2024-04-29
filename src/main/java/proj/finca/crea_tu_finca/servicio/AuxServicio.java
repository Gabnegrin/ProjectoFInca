package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import proj.finca.crea_tu_finca.dto.ClienteDTO2;
import proj.finca.crea_tu_finca.dto.PropiedadDTO2;
import proj.finca.crea_tu_finca.dto.PropietarioDTO2;
import proj.finca.crea_tu_finca.dto.SolicitudDTO2;
import proj.finca.crea_tu_finca.entidades.Cliente;
import proj.finca.crea_tu_finca.entidades.Propiedad;
import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.entidades.Solicitud;
import proj.finca.crea_tu_finca.repositorio.repocliente;
import proj.finca.crea_tu_finca.repositorio.repopropiedad;
import proj.finca.crea_tu_finca.repositorio.repopropietario;
import proj.finca.crea_tu_finca.repositorio.reposolicitud;

public class AuxServicio {
    repopropietario propietariorepositorio;
    repocliente clienterepositorio;
    repopropiedad propiedadrepositorio;
    reposolicitud solicitudrepositorio;
    ModelMapper modelMapper;

    @Autowired
    AuxServicio(repocliente clienterepositorio, ModelMapper modelMapper, repopropiedad propiedadrepositorio, repopropietario propietariorepositorio, reposolicitud solcitudrepositorio){
        this.clienterepositorio = clienterepositorio;
        this.modelMapper = modelMapper;
        this.propiedadrepositorio = propiedadrepositorio;
        this.propietariorepositorio = propietariorepositorio;
        this.solicitudrepositorio = solcitudrepositorio;
    }

    public ClienteDTO2 ClientegetByUsernameAndPassword(Cliente cliente) {
        String username = cliente.getUsuario();
        String password = cliente.getContrasena();
    
        Optional<Cliente> clienteOpt = clienterepositorio.findByUsuarioAndContrasena(username, password);
    
        if (clienteOpt.isPresent()) {
            Cliente client = clienteOpt.get();
            return modelMapper.map(client, ClienteDTO2.class);
        } else {
            return new ClienteDTO2();
        }
    }
    public PropietarioDTO2 PropietariogetByUsernameAndPassword(Propietario propietario) {
        String username = propietario.getUsuario();
        String password = propietario.getContrasena();
    
        Optional<Propietario> propietarioOpt = propietariorepositorio.findByUsuarioAndContrasena(username, password);
    
        if (propietarioOpt.isPresent()) {
            Propietario propiet = propietarioOpt.get();
            return modelMapper.map(propiet, PropietarioDTO2.class);
        } else {
            return new PropietarioDTO2();
        }
    }
    
    public List<SolicitudDTO2> getByPropietarioId(Long propietarioId) {
        List<Solicitud> solicitudes = solicitudrepositorio.findByPropietarioId(propietarioId);
        return solicitudes.stream()
                .map(solicitud -> modelMapper.map(solicitud, SolicitudDTO2.class))
                .collect(Collectors.toList());
    }
    public List<SolicitudDTO2> getByClienteId(Long clienteId) {
        List<Solicitud> solicitudes = solicitudrepositorio.findByClienteId(clienteId);
        return solicitudes.stream()
                .map(solicitud -> modelMapper.map(solicitud, SolicitudDTO2.class))
                .collect(Collectors.toList());
    }
    public List<PropiedadDTO2> propiedadesgetByPropietarioId(Long propietarioId) {
        List<Propiedad> propiedades = propiedadrepositorio.findByPropietarioId(propietarioId);
        return propiedades.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO2.class))
                .collect(Collectors.toList());
    }

}
