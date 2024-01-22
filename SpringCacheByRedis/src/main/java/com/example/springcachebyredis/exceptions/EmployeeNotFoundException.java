package com.example.springcachebyredis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
      @Serial
      private static final long serialVersionUID = -2303908037988492188L;

      public EmployeeNotFoundException(String message) {
            super(message);
      }
}
