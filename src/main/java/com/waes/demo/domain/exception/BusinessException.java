package com.waes.demo.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public abstract class BusinessException extends RuntimeException {

  private final String errorCode;

  private final String errorMessage;

  private final HttpStatus statusCode;

}
