package com.kayaspring.kayaspring.Middlewares;

import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Middlewares.Logging.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ILogger logger;

    @Autowired
    public GlobalExceptionHandler(ILogger logService) {
        this.logger = logService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResultClass> handleException(Exception ex) {

        GenericResultClass genericResult = GenericResultClass.Error(ex,logger);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResult);
    }







}



