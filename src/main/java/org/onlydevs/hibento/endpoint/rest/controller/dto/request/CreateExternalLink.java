package org.onlydevs.hibento.endpoint.rest.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateExternalLink {
  @NotBlank(message = "Type cannot be blank")
  private String type;

  @URL(message = "Incorrect URL format")
  @NotBlank(message = "URL must be provided")
  private String url;
}
