package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.finca.crea_tu_finca.entidades.Solicitud;
import proj.finca.crea_tu_finca.dto.SolicitudDTO;
import proj.finca.crea_tu_finca.dto.SolicitudDTO2;
import proj.finca.crea_tu_finca.repositorio.reposolicitud;

@Service
public class SolicitudServicio {

    private final reposolicitud solicitudRepositorio;
    private final ModelMapper modelMapper;

    @Autowired
    public SolicitudServicio(reposolicitud solicitudRepositorio, ModelMapper modelMapper) {
        this.solicitudRepositorio = solicitudRepositorio;
        this.modelMapper = modelMapper;
    }

    @SuppressWarnings("null")
    public SolicitudDTO2 get(Long id) {
        Optional<Solicitud> solicitudOpt = solicitudRepositorio.findById(id);
        return solicitudOpt.map(solicitud -> modelMapper.map(solicitud, SolicitudDTO2.class)).orElse(null);
    }

    public List<SolicitudDTO2> getAll() {
        List<Solicitud> solicitudes = (List<Solicitud>) solicitudRepositorio.findAll();
        return solicitudes.stream().map(solicitud -> modelMapper.map(solicitud, SolicitudDTO2.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public SolicitudDTO2 save(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        solicitud = solicitudRepositorio.save(solicitud);
        return modelMapper.map(solicitud, SolicitudDTO2.class);
    }

    @SuppressWarnings("null")
    public SolicitudDTO2 update(Solicitud solicitud) {
        Optional<Solicitud> solicitudOptional = solicitudRepositorio.findById(solicitud.getId());
    
        if (solicitudOptional.isPresent()) {
            Solicitud solicitudDB = solicitudOptional.get();
            
            if (solicitud.getC_cliente() != 0) {
                solicitudDB.setC_cliente(solicitud.getC_cliente());
            }
            if (solicitud.getC_propietario() != 0) {
                solicitudDB.setC_propietario(solicitud.getC_propietario());
            }
            if (solicitud.getC_propiedad() != 0) {
                solicitudDB.setC_propiedad(solicitud.getC_propiedad());
            }
            if (solicitud.getPreciot() != 0) {
                solicitudDB.setPreciot(solicitud.getPreciot());
            }
            if (solicitud.getEntrada() != null) {
                solicitudDB.setEntrada(solicitud.getEntrada());
            }
            if (solicitud.getSalida() != null) {
                solicitudDB.setSalida(solicitud.getSalida());
            }
            if (solicitud.getEstado() != 0) {
                solicitudDB.setEstado(solicitud.getEstado());
            }
            if (solicitud.isEliminado() != solicitudDB.isEliminado()) {
                solicitudDB.setEliminado(solicitud.isEliminado());
            }
            Solicitud solicitudActualizada = solicitudRepositorio.save(solicitudDB);
            SolicitudDTO2 solicitudDTO = modelMapper.map(solicitudActualizada, SolicitudDTO2.class);
            
            return solicitudDTO;
        } else {
            return null;
        }
    }

    @SuppressWarnings("null")
    public void delete(Long id) {
        Optional<Solicitud> solicitudOpt = solicitudRepositorio.findById(id);
        solicitudOpt.ifPresent(solicitud -> {
            solicitud.setEliminado(true);
            solicitudRepositorio.save(solicitud);
        });
    }
}
