package proj.finca.crea_tu_finca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import proj.finca.crea_tu_finca.dto.TokenDTO;
import proj.finca.crea_tu_finca.dto.TokenDTO2;
import proj.finca.crea_tu_finca.servicio.JWTTokenService;
import proj.finca.crea_tu_finca.dto.ClienteDTO2;
import proj.finca.crea_tu_finca.dto.PropietarioDTO2;

@RestController
@RequestMapping(value = "/api/javeriana/grupo25/jwt")
public class JWTcontroller {

    @Autowired
    JWTTokenService jwtTokenService;

    @CrossOrigin
    @PostMapping(  value = "/authcliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO autenticar( @RequestBody ClienteDTO2 usuarioDTO ){
        return new TokenDTO(jwtTokenService.generarToken(usuarioDTO), usuarioDTO);
    }

    @CrossOrigin
    @PostMapping(  value = "/authpropietario", produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO2 autenticar2( @RequestBody PropietarioDTO2 usuarioDTO ){
        return new TokenDTO2(jwtTokenService.generarToken2(usuarioDTO), usuarioDTO);
    }

}
