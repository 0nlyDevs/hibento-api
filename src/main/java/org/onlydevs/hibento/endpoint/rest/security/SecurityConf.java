package org.onlydevs.hibento.endpoint.rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConf {

  private final AdminChecker adminChecker;

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
        .oauth2Login(oauth2 -> oauth2.successHandler(authenticationSuccessHandler()))
        .logout(LogoutConfigurer::permitAll)
        .csrf(
            csrf ->
                csrf.ignoringRequestMatchers(
                    "/events/**", "/sessions/**", "/speakers/**", "/rooms/**"));

    return http.build();
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return (request, response, authentication) -> {
      var oidcUser = (OidcUser) authentication.getPrincipal();
      String email = oidcUser.getEmail();
      if (!adminChecker.isAdmin(email)) {
        throw new AccessDeniedException("You're not a Vola Administrator");
      }
      response.sendRedirect("/payments");
    };
  }
}
