package org.onlydevs.hibento.endpoint.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pagination {
  private Integer page;
  private Integer limit;
  private long total;
}
