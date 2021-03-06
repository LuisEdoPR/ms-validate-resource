package com.waes.demo.domain.exception;

import static com.waes.demo.infrastructure.DetailErrorEnum.JSON_SIZE_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public final class JsonDifferentSizeException extends BusinessException {

  public JsonDifferentSizeException(String resourceId) {
    super(JSON_SIZE_ERROR.getCode(),
        JSON_SIZE_ERROR.getDescription(),
        HttpStatus.BAD_REQUEST);
    log.error("{} - {}", JSON_SIZE_ERROR.getDescription(), resourceId);
  }

}
