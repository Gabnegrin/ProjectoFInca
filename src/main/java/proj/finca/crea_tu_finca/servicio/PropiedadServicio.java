package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.finca.crea_tu_finca.entidades.Propiedad;
import proj.finca.crea_tu_finca.dto.PropiedadDTO;
import proj.finca.crea_tu_finca.repositorio.repopropiedad;

@Service
public class PropiedadServicio {

    private final repopropiedad propiedadRepositorio;
    private final ModelMapper modelMapper;

    @Autowired
    public PropiedadServicio(repopropiedad propiedadRepositorio, ModelMapper modelMapper) {
        this.propiedadRepositorio = propiedadRepositorio;
        this.modelMapper = modelMapper;
    }

    @SuppressWarnings("null")
    public PropiedadDTO get(Long id) {
        Optional<Propiedad> propiedadOpt = propiedadRepositorio.findById(id);
        return propiedadOpt.map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class)).orElse(null);
    }

    public List<PropiedadDTO> getAll() {
        List<Propiedad> propiedades = (List<Propiedad>) propiedadRepositorio.findAll();
        return propiedades.stream().map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public PropiedadDTO save(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad = propiedadRepositorio.save(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }

    @SuppressWarnings("null")
    public PropiedadDTO update(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad = propiedadRepositorio.save(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }

    @SuppressWarnings("null")
    public void delete(Long id) {
        Optional<Propiedad> propiedadOpt = propiedadRepositorio.findById(id);
        propiedadOpt.ifPresent(propiedad -> {
            propiedad.setEliminado(true);
            propiedadRepositorio.save(propiedad);
        });
    }
}
