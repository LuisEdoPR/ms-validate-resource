package com.waes.demo.infrastructure;

import com.waes.demo.domain.exception.BusinessException;
import com.waes.demo.domain.exception.JsonDifferentContentException;
import com.waes.demo.infrastructure.api.ErrorResponse;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExposeControllerAdvice {

  /**
   * This method is used to get only one ErrorResponse.
   *
   * @param exception This is the first parameter to method.
   * @return one ErrorResponse.
   */
  @ExceptionHandler(JsonDifferentContentException.class)
  public ErrorResponse<Map<String, Object>> handleException(JsonDifferentContentException exception) {
    String errorCode = exception.getErrorCode();
    String exceptionMessage = exception.getErrorMessage();
    Map<String, Object> data = exception.getDiffs();
    return new ErrorResponse<>(errorCode, exceptionMessage, data);
  }

  /**
   * This method is used for BusinessException.
   *
   * @param exception This is the first parameter to method.
   * @return one ErrorResponse.
   */
  @ExceptionHandler(BusinessException.class)
  public ErrorResponse<Object> handleException(BusinessException exception) {
    return buildErrorResponse(exception);
  }

  /**
   * This method is used to get only one ErrorResponse.
   *
   * @param exception This is the first parameter to method.
   * @return one ErrorResponse.
   */
  @ExceptionHandler(Exception.class)
  public ErrorResponse<Object> handleException(RuntimeException exception) {
    return buildErrorResponse(exception);
  }

  private ErrorResponse<Object> buildErrorResponse(Exception exception) {
    String errorCode = DetailErrorEnum.GENERIC_ERROR.getCode();
    String exceptionMessage = DetailErrorEnum.GENERIC_ERROR.getDescription();
    if (exception instanceof BusinessException) {
      errorCode = ((BusinessException) exception).getErrorCode();
      exceptionMessage = ((BusinessException) exception).getErrorMessage();
    }
    log.error("Error Handler : " + exceptionMessage, exception);
    return ErrorResponse
        .builder()
        .code(errorCode)
        .message(exceptionMessage)
        .build();
  }

}
