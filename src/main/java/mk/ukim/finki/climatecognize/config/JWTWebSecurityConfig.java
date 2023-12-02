package mk.ukim.finki.climatecognize.config;



import lombok.AllArgsConstructor;


import mk.ukim.finki.climatecognize.config.filters.JWTAuthorizationFilter;
import mk.ukim.finki.climatecognize.config.filters.JwtAuthenticationFilter;
import mk.ukim.finki.climatecognize.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Order(200)
@Configuration
@AllArgsConstructor
@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableMethodSecurity
public class JWTWebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .requestMatchers("/assets/**", "/api/auth/register","/api/auth/login","/api/climate/**", "/error").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/climate/dataset").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager, userService, passwordEncoder))
                .addFilter(new JWTAuthorizationFilter(authenticationManager, userService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTAuthorizationFilter authorizationFilter(AuthenticationManager authenticationManager) throws Exception {
        return new JWTAuthorizationFilter(authenticationManager, userService);
    }

    @Bean
    public JwtAuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        return new JwtAuthenticationFilter(authenticationManager, userService, passwordEncoder);
    }

}

