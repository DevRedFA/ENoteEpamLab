package com.epam.controller.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class EndpointExceptionHandler {

  @ExceptionHandler(value = NullPointerException.class)
  public void nullPointerExceptionHandler(HttpServletResponse response,
                                          NullPointerException exception) {
    try {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
      log.error("NullPointerException!", exception.getMessage());
    } catch (IOException e) {
      log.error("NPEHandler Exception!", e.getMessage());
      e.printStackTrace();
    }
  }

  @ExceptionHandler(value = SecurityException.class)
  public void securityExceptionHandler(HttpServletResponse response, SecurityException exception) {
    try {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
      log.error("SecurityException!", exception.getMessage());
    } catch (IOException e) {
      log.error("SecurityExceptionHandler Exception!", e.getMessage());
      e.printStackTrace();
    }
  }

  @ExceptionHandler(value = UnsupportedOperationException.class)
  public void unsupportedOperationExceptionHandler(HttpServletResponse response,
                                                   UnsupportedOperationException exception) {
    try {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
      log.error("UnsupportedOperationException!", exception.getMessage());
    } catch (IOException e) {
      log.error("UnsupportedExceptionHandler Exception!", e.getMessage());
      e.printStackTrace();
    }
  }

  @ExceptionHandler(value = Exception.class)
  public void exchangeException(HttpServletResponse response, Exception exception) {
    exception.printStackTrace();
    try {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage());
      log.error("ExchangeException!", exception.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
      log.error("ExchangeHandler Exception!", exception.getMessage());
    }
  }
}
