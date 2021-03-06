package com.waes.demo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@Builder
@ToString
@AllArgsConstructor
@Schema(description = "Resource contains Id, Left and Right Data encoded to validate")
public final class Resource {

  @Schema(
      description = "ID of resource",
      example = "1",
      required = true)
  private final String id;

  @Schema(
      description = "left json encoded base64",
      example = "ewogICAgImtleSI6ICJ2YWx1ZSIKfQ==")
  @Setter
  private String leftData;

  @Schema(
      description = "right json encoded base64",
      example = "ewogICAgImtleSI6ICJ2YWx1ZSIKfQ==")
  @Setter
  private String rightData;


  public Resource(String id) {
    this.id = id;
  }

  public static Resource create(String id, String leftData, String rightData) {
    return Resource.builder()
        .id(id)
        .leftData(leftData)
        .rightData(rightData)
        .build();
  }

  public boolean hasLeft() {
    return this.leftData != null && !this.leftData.isBlank();
  }

  public boolean hasRight() {
    return this.rightData != null && !this.rightData.isBlank();
  }

}
