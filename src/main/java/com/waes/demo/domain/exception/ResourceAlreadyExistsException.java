package com.waes.demo.domain.exception;

import static com.waes.demo.infrastructure.DetailErrorEnum.RESOURCE_ALREADY_EXISTS_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public final class ResourceAlreadyExistsException extends BusinessException {

  public ResourceAlreadyExistsException(String resourceId, String side) {
    super(RESOURCE_ALREADY_EXISTS_ERROR.getCode(),
        RESOURCE_ALREADY_EXISTS_ERROR.getDescription(),
        HttpStatus.BAD_REQUEST);
    log.error("{} - {} - {}", resourceId, side, RESOURCE_ALREADY_EXISTS_ERROR.getDescription());
  }

}
