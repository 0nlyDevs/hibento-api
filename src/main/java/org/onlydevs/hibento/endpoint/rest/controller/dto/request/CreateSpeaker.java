package org.onlydevs.hibento.endpoint.rest.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSpeaker {
  @NotBlank(message = "Speaker name cannot be blank")
  private String name;

  @URL(message = "Invalid url")
  private String avatarUrl;

  private String bio;
  @Valid private List<CreateExternalLink> externalLinks;
}
