package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.finca.crea_tu_finca.entidades.Propiedad;
import proj.finca.crea_tu_finca.dto.PropiedadDTO;
import proj.finca.crea_tu_finca.dto.PropiedadDTO2;
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
    public PropiedadDTO2 get(Long id) {
        Optional<Propiedad> propiedadOpt = propiedadRepositorio.findById(id);
        return propiedadOpt.map(propiedad -> modelMapper.map(propiedad, PropiedadDTO2.class)).orElse(null);
    }

    public List<PropiedadDTO2> getAll() {
        List<Propiedad> propiedades = (List<Propiedad>) propiedadRepositorio.findAll();
        return propiedades.stream().map(propiedad -> modelMapper.map(propiedad, PropiedadDTO2.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public PropiedadDTO2 save(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad = propiedadRepositorio.save(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO2.class);
    }

    @SuppressWarnings("null")
    public PropiedadDTO2 update(Propiedad propiedadd) {
        Optional<Propiedad> propiedadOptional = propiedadRepositorio.findById(propiedadd.getId());
    
        if (propiedadOptional.isPresent()) {
            Propiedad propiedadDB = propiedadOptional.get();
            
            if (propiedadd.getNombre() != null) {
                propiedadDB.setNombre(propiedadd.getNombre());
            }
            if (propiedadd.getVisitas() != 0) {
                propiedadDB.setVisitas(propiedadd.getVisitas());
            }
            if (propiedadd.getCalificacion() != 0) {
                propiedadDB.setCalificacion(propiedadd.getCalificacion());
            }
            if (propiedadd.getValordia() != 0) {
                propiedadDB.setValordia(propiedadd.getValordia());
            }
            if (propiedadd.isEliminado() != propiedadDB.isEliminado()) {
                propiedadDB.setEliminado(propiedadd.isEliminado());
            }
            

            Propiedad propiedadActualizada = propiedadRepositorio.save(propiedadDB);
            PropiedadDTO2 propiedadDTO = modelMapper.map(propiedadActualizada, PropiedadDTO2.class);
            
            return propiedadDTO;
        } else {
            return null;
        }
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
