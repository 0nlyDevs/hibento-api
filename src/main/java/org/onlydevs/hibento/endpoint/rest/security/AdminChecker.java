package org.onlydevs.hibento.endpoint.rest.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminChecker {

  @Value("${hibento.admins:}")
  private String admins;

  public boolean isAdmin(String email) {
    if (email == null || admins == null || admins.isEmpty()) {
      return false;
    }
    return Arrays.stream(admins.split(","))
        .map(String::trim)
        .map(String::toLowerCase)
        .anyMatch(admin -> admin.equals(email.toLowerCase()));
  }
}
