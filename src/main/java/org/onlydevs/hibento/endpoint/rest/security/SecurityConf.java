package org.onlydevs.hibento.endpoint.rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConf {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/ping", "/health/**")
                    .permitAll()
                    .requestMatchers("/events/**", "/sessions/**", "/speakers/**", "/rooms/**")
                    .authenticated()
                    .anyRequest()
                    .denyAll())
        .logout(LogoutConfigurer::permitAll)
        .csrf(
            csrf ->
                csrf.ignoringRequestMatchers(
                    "/events/**", "/sessions/**", "/speakers/**", "/rooms/**"));

    return http.build();
  }
}
