package com.template.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class NotFoundException extends TemplateException {

  public NotFoundException(String message) {
    super(message);
  }

}