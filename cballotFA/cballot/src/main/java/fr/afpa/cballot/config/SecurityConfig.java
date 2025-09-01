package fr.afpa.cballot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // module vote en accès public via token (lien email)
                        .requestMatchers("/api/vote/**").permitAll()
                        .requestMatchers("/actuator/health").permitAll()
                        // administration protégée (Basic Auth)
                        .requestMatchers("/api/admin/**").authenticated()
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // Pour la démo : un utilisateur admin mémoire. À remplacer par un annuaire ou
    // un SSO si besoin.
    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("admin") // en clair pour la démo ; utilisez un PasswordEncoder en prod !
                        .roles("ADMIN")
                        .build());
    }
}