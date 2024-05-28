package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import proj.finca.crea_tu_finca.entidades.Solicitud;
import proj.finca.crea_tu_finca.exceptions.ForbiddenException;
import proj.finca.crea_tu_finca.exceptions.UnauthorizedException;
import proj.finca.crea_tu_finca.dto.SolicitudDTO;
import proj.finca.crea_tu_finca.dto.SolicitudDTO2;
import proj.finca.crea_tu_finca.repositorio.reposolicitud;

@Service
public class SolicitudServicio {

    private final reposolicitud solicitudRepositorio;
    private final ModelMapper modelMapper;
    JWTTokenService tokenService;

    @Autowired
    public SolicitudServicio(reposolicitud solicitudRepositorio, ModelMapper modelMapper, JWTTokenService tokenservice) {
        this.solicitudRepositorio = solicitudRepositorio;
        this.modelMapper = modelMapper;
        this.tokenService = tokenservice;
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



    //jwt





    @SuppressWarnings("null")
    public SolicitudDTO2 get(Long id, String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid authorization header");
            }
    
            String token = authorizationHeader.substring(7);
    
            Claims claims;
            try {
                claims = this.tokenService.decodificarToken(token);
            } catch (Exception e) {
                throw new UnauthorizedException("Invalid JWT token: " + e.getMessage());
            }
    
            List<String> authorities = (List<String>) claims.get("authorities");
            if (authorities != null && !authorities.isEmpty()) {
                if (!authorities.contains("ROLE_CLIENTE") && !authorities.contains("ROLE_PROPIETARIO")) {
                    throw new ForbiddenException("Insufficient permissions");
                }
        }
        Optional<Solicitud> solicitudOpt = solicitudRepositorio.findById(id);
        return solicitudOpt.map(solicitud -> modelMapper.map(solicitud, SolicitudDTO2.class)).orElse(null);
    }

    public List<SolicitudDTO2> getAll(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid authorization header");
            }
    
            String token = authorizationHeader.substring(7);
    
            Claims claims;
            try {
                claims = this.tokenService.decodificarToken(token);
            } catch (Exception e) {
                throw new UnauthorizedException("Invalid JWT token: " + e.getMessage());
            }
    
            List<String> authorities = (List<String>) claims.get("authorities");
            if (authorities != null && !authorities.isEmpty()) {
                if (!authorities.contains("ROLE_CLIENTE") && !authorities.contains("ROLE_PROPIETARIO")) {
                    throw new ForbiddenException("Insufficient permissions");
                }
        }
        List<Solicitud> solicitudes = (List<Solicitud>) solicitudRepositorio.findAll();
        return solicitudes.stream().map(solicitud -> modelMapper.map(solicitud, SolicitudDTO2.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public SolicitudDTO2 save(SolicitudDTO solicitudDTO, String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid authorization header");
            }
    
            String token = authorizationHeader.substring(7);
    
            Claims claims;
            try {
                claims = this.tokenService.decodificarToken(token);
            } catch (Exception e) {
                throw new UnauthorizedException("Invalid JWT token: " + e.getMessage());
            }
    
            List<String> authorities = (List<String>) claims.get("authorities");
            if (authorities != null && !authorities.isEmpty()) {
                if (!authorities.contains("ROLE_CLIENTE") && !authorities.contains("ROLE_PROPIETARIO")) {
                    throw new ForbiddenException("Insufficient permissions");
                }
        }
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        solicitud = solicitudRepositorio.save(solicitud);
        return modelMapper.map(solicitud, SolicitudDTO2.class);
    }

    @SuppressWarnings("null")
    public SolicitudDTO2 update(Solicitud solicitud, String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid authorization header");
            }
    
            String token = authorizationHeader.substring(7);
    
            Claims claims;
            try {
                claims = this.tokenService.decodificarToken(token);
            } catch (Exception e) {
                throw new UnauthorizedException("Invalid JWT token: " + e.getMessage());
            }
    
            List<String> authorities = (List<String>) claims.get("authorities");
            if (authorities != null && !authorities.isEmpty()) {
                if (!authorities.contains("ROLE_CLIENTE") && !authorities.contains("ROLE_PROPIETARIO")) {
                    throw new ForbiddenException("Insufficient permissions");
                }
        }
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
    public void delete(Long id, String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid authorization header");
            }
    
            String token = authorizationHeader.substring(7);
    
            Claims claims;
            try {
                claims = this.tokenService.decodificarToken(token);
            } catch (Exception e) {
                throw new UnauthorizedException("Invalid JWT token: " + e.getMessage());
            }
    
            List<String> authorities = (List<String>) claims.get("authorities");
            if (authorities != null && !authorities.isEmpty()) {
                if (!authorities.contains("ROLE_CLIENTE") && !authorities.contains("ROLE_PROPIETARIO")) {
                    throw new ForbiddenException("Insufficient permissions");
                }
        }
        Optional<Solicitud> solicitudOpt = solicitudRepositorio.findById(id);
        solicitudOpt.ifPresent(solicitud -> {
            solicitud.setEliminado(true);
            solicitudRepositorio.save(solicitud);
        });
    }

}
