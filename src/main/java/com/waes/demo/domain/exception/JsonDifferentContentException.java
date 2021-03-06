package com.waes.demo.domain.exception;

import static com.waes.demo.infrastructure.DetailErrorEnum.JSON_CONTAIN_ERROR;

import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public final class JsonDifferentContentException extends BusinessException {

  private final Map<String, Object> diffs;

  public JsonDifferentContentException(String resourceId, Map<String, Object> diffs) {
    super(JSON_CONTAIN_ERROR.getCode(),
        JSON_CONTAIN_ERROR.getDescription(),
        HttpStatus.BAD_REQUEST);
    this.diffs = diffs;
    log.error("{} - {} - {}", JSON_CONTAIN_ERROR.getDescription(), resourceId, this.diffs);
  }

}
