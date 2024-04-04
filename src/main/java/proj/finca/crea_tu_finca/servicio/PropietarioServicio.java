package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.dto.PropietarioDTO;
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
    public PropietarioDTO get(Long id) {
        Optional<Propietario> propietarioOpt = propietarioRepositorio.findById(id);
        return propietarioOpt.map(propietario -> modelMapper.map(propietario, PropietarioDTO.class)).orElse(null);
    }

    public List<PropietarioDTO> getAll() {
        List<Propietario> propietarios = (List<Propietario>) propietarioRepositorio.findAll();
        return propietarios.stream().map(propietario -> modelMapper.map(propietario, PropietarioDTO.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public PropietarioDTO save(PropietarioDTO propietarioDTO) {
        Propietario propietario = modelMapper.map(propietarioDTO, Propietario.class);
        propietario = propietarioRepositorio.save(propietario);
        return modelMapper.map(propietario, PropietarioDTO.class);
    }

    @SuppressWarnings("null")
    public PropietarioDTO update(PropietarioDTO propietarioDTO) {
        Propietario propietario = modelMapper.map(propietarioDTO, Propietario.class);
        propietario = propietarioRepositorio.save(propietario);
        return modelMapper.map(propietario, PropietarioDTO.class);
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
