package com.waes.demo.domain.exception;

import static com.waes.demo.infrastructure.DetailErrorEnum.RESOURCE_NOT_FOUND_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public final class ResourceNotFoundException extends BusinessException {

  public ResourceNotFoundException(String resourceId) {
    super(RESOURCE_NOT_FOUND_ERROR.getCode(),
        RESOURCE_NOT_FOUND_ERROR.getDescription(),
        HttpStatus.BAD_REQUEST);
    log.error("{} - {}", resourceId, RESOURCE_NOT_FOUND_ERROR.getDescription());
  }

}
