package com.waes.demo.infrastructure.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Object JsonDTO with JsonEncoded in base64")
public class JsonDTO {

  @Schema(
      description = "json encoded base64",
      example = "ewogICAgImtleSI6ICJ2YWx1ZSIKfQ==",
      required = true)
  @NotNull(message = "value is required")
  private String value;

}
