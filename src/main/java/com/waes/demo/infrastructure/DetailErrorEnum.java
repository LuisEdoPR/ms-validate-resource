package com.waes.demo.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DetailErrorEnum {

  GENERIC_ERROR("WAES-001", "WAES Demo Generic Error"),
  RESOURCE_NOT_FOUND_ERROR("WAES-002", "Resource not found"),
  RESOURCE_HAS_NO_LEFT_ERROR("WAES-003", "Resource has no left data"),
  RESOURCE_HAS_NO_RIGHT_ERROR("WAES-004", "Resource has no right data"),
  RESOURCE_ALREADY_EXISTS_ERROR("WAES-005", "Resource already exists"),
  JSON_FORMAT_INVALID_ERROR("WAES-006", "Json format invalid"),
  JSON_SIZE_ERROR("WAES-007", "Json objects have different sizes"),
  JSON_CONTAIN_ERROR("WAES-008", "Json objects are different"),
  ;

  private final String code;

  private final String description;

}
