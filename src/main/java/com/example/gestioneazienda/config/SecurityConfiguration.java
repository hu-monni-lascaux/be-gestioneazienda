package com.example.gestioneazienda.config;

import com.example.gestioneazienda.filter.JwtAuthenticationEntryPoint;
import com.example.gestioneazienda.filter.JwtAuthenticationFilter;
import com.example.gestioneazienda.mapper.UserMapper;
import com.example.gestioneazienda.repository.TokenRepository;
import com.example.gestioneazienda.repository.UserRepository;
import com.example.gestioneazienda.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/**").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/user/*").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/agenda/**").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/agenda/*").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/agenda/*").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/agenda/*").hasAnyAuthority("ADMIN", "BASE")

                        .requestMatchers(HttpMethod.GET, "/api/v1/appointment/**").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/appointment/**").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/appointment/**").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/appointment/**").hasAnyAuthority("ADMIN", "BASE")

                        .requestMatchers(HttpMethod.GET, "/api/v1/service-hour/**").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/service-hour/**").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/service-hour/**").hasAnyAuthority("ADMIN", "BASE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/service-hour/**").hasAnyAuthority("ADMIN", "BASE")

                        .anyRequest().authenticated()
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        ;
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService(), tokenRepository);
    }


}
