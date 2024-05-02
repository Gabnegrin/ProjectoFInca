package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.dto.PropietarioDTO2;
import proj.finca.crea_tu_finca.repositorio.repopropietario;

@Service
public class PropietarioServicio {

    private final repopropietario propietarioRepositorio;
    private final ModelMapper modelMapper;

    @Autowired
    public PropietarioServicio(repopropietario propietarioRepositorio, ModelMapper modelMapper) {
        this.propietarioRepositorio = propietarioRepositorio;
        this.modelMapper = modelMapper;
    }

    @SuppressWarnings("null")
    public PropietarioDTO2 get(Long id) {
        Optional<Propietario> propietarioOpt = propietarioRepositorio.findById(id);
        return propietarioOpt.map(propietario -> modelMapper.map(propietario, PropietarioDTO2.class)).orElse(null);
    }

    public List<PropietarioDTO2> getAll() {
        List<Propietario> propietarios = (List<Propietario>) propietarioRepositorio.findAll();
        return propietarios.stream().map(propietario -> modelMapper.map(propietario, PropietarioDTO2.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public PropietarioDTO2 save(Propietario propietarioo) {
        Propietario propietario = propietarioo;
        propietario = propietarioRepositorio.save(propietario);
        return modelMapper.map(propietario, PropietarioDTO2.class);
    }

    @SuppressWarnings("null")
    public PropietarioDTO2 update(Propietario propietarioo) {
        Optional<Propietario> propietarioOptional = propietarioRepositorio.findById(propietarioo.getId());
    
        if (propietarioOptional.isPresent()) {
            Propietario propietarioDB = propietarioOptional.get();
            
            // Comparar y actualizar las propiedades
            if (propietarioo.getNombre() != null) {
                propietarioDB.setNombre(propietarioo.getNombre());
            }
            if (propietarioo.getApellido() != null) {
                propietarioDB.setApellido(propietarioo.getApellido());
            }
            if (propietarioo.getCorreo() != null) {
                propietarioDB.setCorreo(propietarioo.getCorreo());
            }
            if (propietarioo.getUsuario() != null) {
                propietarioDB.setUsuario(propietarioo.getUsuario());
            }
            if (propietarioo.getContrasena() != null) {
                propietarioDB.setContrasena(propietarioo.getContrasena());
            }
            if (propietarioo.getEdad() != 0) {
                propietarioDB.setEdad(propietarioo.getEdad());
            }
            if (propietarioo.getCalificacion() != 0) {
                propietarioDB.setCalificacion(propietarioo.getCalificacion());
            }
            if (propietarioo.isEliminado() != propietarioDB.isEliminado()) {
                propietarioDB.setEliminado(propietarioo.isEliminado());
            }
            
            Propietario propietarioActualizado = propietarioRepositorio.save(propietarioDB);
            PropietarioDTO2 propietarioDTO = modelMapper.map(propietarioActualizado, PropietarioDTO2.class);
            
            return propietarioDTO;
        } else {
            return null;
        }
    }

    @SuppressWarnings("null")
    public void delete(Long id) {
        Optional<Propietario> propietarioOpt = propietarioRepositorio.findById(id);
        propietarioOpt.ifPresent(propietario -> {
            propietario.setEliminado(true);
            propietarioRepositorio.save(propietario);
        });
    }
}
