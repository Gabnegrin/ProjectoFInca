package proj.finca.crea_tu_finca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


import proj.finca.crea_tu_finca.filtro.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements ISecurityConfig {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

	@Override
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

	
	@Override
    @Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        
    
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class).
                                csrf(csrf -> csrf.ignoringRequestMatchers(ignoreSpecificRequests()));
		return http.build();
	}


	private RequestMatcher ignoreSpecificRequests() {
        return new OrRequestMatcher(
            new AntPathRequestMatcher("/api/javeriana/grupo25/aux/cliente/login", HttpMethod.GET.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/aux/cliente/login", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/aux/cliente/login", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/aux/cliente/login", HttpMethod.DELETE.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/aux/propietario/login", HttpMethod.GET.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/aux/propietario/login", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/aux/propietario/login", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/aux/propietario/login", HttpMethod.DELETE.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/jwt/**", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/jwt/**", HttpMethod.DELETE.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/jwt/**", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/jwt/**", HttpMethod.GET.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/cliente", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/cliente", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/solicitud", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/solicitud", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/propiedad", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/propiedad", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/propietario", HttpMethod.PUT.name()),
            new AntPathRequestMatcher("/api/javeriana/grupo25/propietario", HttpMethod.POST.name())
        );
    }
}
