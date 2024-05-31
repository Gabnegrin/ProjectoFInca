package proj.finca.crea_tu_finca.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import proj.finca.crea_tu_finca.entidades.Propiedad;
import proj.finca.crea_tu_finca.exceptions.ForbiddenException;
import proj.finca.crea_tu_finca.exceptions.UnauthorizedException;
import proj.finca.crea_tu_finca.dto.PropiedadDTO;
import proj.finca.crea_tu_finca.dto.PropiedadDTO2;
import proj.finca.crea_tu_finca.repositorio.repopropiedad;

@Service
public class PropiedadServicio {

    private final repopropiedad propiedadRepositorio;
    private final ModelMapper modelMapper;
    JWTTokenService tokenService;

    @Autowired
    public PropiedadServicio(repopropiedad propiedadRepositorio, ModelMapper modelMapper, JWTTokenService tokenservice) {
        this.propiedadRepositorio = propiedadRepositorio;
        this.modelMapper = modelMapper;
        this.tokenService = tokenservice;
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




    //jwt





    @SuppressWarnings("null")
    public PropiedadDTO2 get(Long id, String authorizationHeader) {
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
        Optional<Propiedad> propiedadOpt = propiedadRepositorio.findById(id);
        return propiedadOpt.map(propiedad -> modelMapper.map(propiedad, PropiedadDTO2.class)).orElse(null);
    }

    public List<PropiedadDTO2> getAll(String authorizationHeader) {
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
        List<Propiedad> propiedades = (List<Propiedad>) propiedadRepositorio.findAll();
        return propiedades.stream().map(propiedad -> modelMapper.map(propiedad, PropiedadDTO2.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public PropiedadDTO2 update(Propiedad propiedadd, String authorizationHeader) {
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
        Optional<Propiedad> propiedadOpt = propiedadRepositorio.findById(id);
        propiedadOpt.ifPresent(propiedad -> {
            propiedad.setEliminado(true);
            propiedadRepositorio.save(propiedad);
        });
    }
    @SuppressWarnings("null")
    public PropiedadDTO2 save(Propiedad propiedadDTO, String authorizationHeader) {
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
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad = propiedadRepositorio.save(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO2.class);
    }
}
