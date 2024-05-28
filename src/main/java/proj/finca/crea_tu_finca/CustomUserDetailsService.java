package proj.finca.crea_tu_finca;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import proj.finca.crea_tu_finca.entidades.Cliente;
import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.repositorio.repocliente;
import proj.finca.crea_tu_finca.repositorio.repopropietario;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    repocliente repocliente;
    repopropietario repopropietario;
    ModelMapper modelmapper;

    @Autowired
    CustomUserDetailsService(repocliente clienterepositorio, repopropietario repopropietario, ModelMapper modelmapper){
        this.repocliente = clienterepositorio;
        this.repopropietario = repopropietario;
        this.modelmapper = modelmapper;
    }
    
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Optional<Cliente> cliente = repocliente.findByUsuario(usernameOrEmail);
        Optional<Propietario> propietario = repopropietario.findByUsuario(usernameOrEmail);

        Set<GrantedAuthority> authorities =  new HashSet<>();
        if(cliente == null){
            if(propietario == null){
                throw new UsernameNotFoundException("Usuario no encontrado con nombre de usuario o correo electrónico: " + usernameOrEmail);
            }else{
                authorities.add(new SimpleGrantedAuthority("ROLE_PROPIETARIO"));
                
            }
        }else{
            authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
        return new org.springframework.security.core.userdetails.User(
                                                                            usernameOrEmail,
                                                                            usernameOrEmail,
                                                                            authorities
                                                                    );
        
    }
}
