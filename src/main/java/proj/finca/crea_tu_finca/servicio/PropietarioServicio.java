package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.exceptions.ForbiddenException;
import proj.finca.crea_tu_finca.exceptions.UnauthorizedException;
import proj.finca.crea_tu_finca.dto.PropietarioDTO2;
import proj.finca.crea_tu_finca.repositorio.repopropietario;


@Service
public class PropietarioServicio {

    repopropietario propietarioRepositorio;
    ModelMapper modelMapper;
    JWTTokenService tokenService;

    @Autowired
    public PropietarioServicio(repopropietario propietarioRepositorio, ModelMapper modelMapper, JWTTokenService tokenservice) {
        this.propietarioRepositorio = propietarioRepositorio;
        this.modelMapper = modelMapper;
        this.tokenService = tokenservice;
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

    //jwt





    @SuppressWarnings("null")
    public PropietarioDTO2 get(Long id, String authorizationHeader) {
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
            if (!authorities.contains("ROLE_PROPIETARIO")) {
                throw new ForbiddenException("Insufficient permissions");
            }
        }
        Optional<Propietario> propietarioOpt = propietarioRepositorio.findById(id);
        return propietarioOpt.map(propietario -> modelMapper.map(propietario, PropietarioDTO2.class)).orElse(null);
    }

    public List<PropietarioDTO2> getAll(String authorizationHeader) {
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
            if (!authorities.contains("ROLE_PROPIETARIO")) {
                throw new ForbiddenException("Insufficient permissions");
            }
        }
        List<Propietario> propietarios = (List<Propietario>) propietarioRepositorio.findAll();
        return propietarios.stream().map(propietario -> modelMapper.map(propietario, PropietarioDTO2.class))
                .collect(Collectors.toList());
    }

        @SuppressWarnings("null")
    public PropietarioDTO2 update(Propietario propietarioo, String authorizationHeader) {
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
            if (!authorities.contains("ROLE_PROPIETARIO")) {
                throw new ForbiddenException("Insufficient permissions");
            }
        }
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
            if (!authorities.contains("ROLE_PROPIETARIO")) {
                throw new ForbiddenException("Insufficient permissions");
            }
        }
        Optional<Propietario> propietarioOpt = propietarioRepositorio.findById(id);
        propietarioOpt.ifPresent(propietario -> {
            propietario.setEliminado(true);
            propietarioRepositorio.save(propietario);
        });
    }
}
