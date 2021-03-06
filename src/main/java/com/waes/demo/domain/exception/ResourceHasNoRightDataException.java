package com.waes.demo.domain.exception;

import static com.waes.demo.infrastructure.DetailErrorEnum.RESOURCE_ALREADY_EXISTS_ERROR;
import static com.waes.demo.infrastructure.DetailErrorEnum.RESOURCE_HAS_NO_RIGHT_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public final class ResourceHasNoRightDataException extends BusinessException {

  public ResourceHasNoRightDataException(String resourceId) {
    super(RESOURCE_HAS_NO_RIGHT_ERROR.getCode(),
        RESOURCE_HAS_NO_RIGHT_ERROR.getDescription(),
        HttpStatus.BAD_REQUEST);
    log.error("{} - {}", resourceId, RESOURCE_HAS_NO_RIGHT_ERROR.getDescription());
  }

}
