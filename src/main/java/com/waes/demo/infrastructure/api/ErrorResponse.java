package com.waes.demo.infrastructure.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse<T> {

  private String code;

  private String message;

  private T differences;

}
