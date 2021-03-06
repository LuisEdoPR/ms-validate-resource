package com.waes.demo.domain.exception;

import static com.waes.demo.infrastructure.DetailErrorEnum.JSON_FORMAT_INVALID_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public final class JsonInvalidException extends BusinessException {

  public JsonInvalidException(String json) {
    super(JSON_FORMAT_INVALID_ERROR.getCode(),
        JSON_FORMAT_INVALID_ERROR.getDescription(),
        HttpStatus.BAD_REQUEST);
    log.error("{} - {}", JSON_FORMAT_INVALID_ERROR.getDescription(), json);
  }

}
