package be.vinci.ipl.cae.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfiguration to handle security configuration.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  /**
   * Constructor for SecurityConfiguration.
   *
   * @param jwtAuthenticationFilter the injected JwtAuthenticationFilter.
   */
  public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  /**
   * FilterChain for the security configuration.
   *
   * @param http the HttpSecurity.
   * @return the SecurityFilterChain.
   * @throws Exception the exception.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}
