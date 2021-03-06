package com.waes.demo.domain.exception;

import static com.waes.demo.infrastructure.DetailErrorEnum.RESOURCE_ALREADY_EXISTS_ERROR;
import static com.waes.demo.infrastructure.DetailErrorEnum.RESOURCE_HAS_NO_LEFT_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public final class ResourceHasNoLeftDataException extends BusinessException {

  public ResourceHasNoLeftDataException(String resourceId) {
    super(RESOURCE_HAS_NO_LEFT_ERROR.getCode(),
        RESOURCE_HAS_NO_LEFT_ERROR.getDescription(),
        HttpStatus.BAD_REQUEST);
    log.error("{} - {}", resourceId, RESOURCE_HAS_NO_LEFT_ERROR.getDescription());
  }

}
