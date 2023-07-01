package com.kayaspring.kayaspring.Middlewares;

import com.kayaspring.kayaspring.Common.GenericResultClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final LogService logService;

    @Autowired
    public GlobalExceptionHandler(LogService logService) {
        this.logService = logService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResultClass> handleException(Exception ex) {
        logService.log("ERROR", ex.getMessage());
        GenericResultClass genericResult = GenericResultClass.Error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResult);
    }
}

