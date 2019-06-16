package com.tangxc.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = {"com.tangxc.manager.controller"})
public class ErrorControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception e) {
        Map<String, Object> attributes = new HashMap<>();
        String errorCode = e.getMessage();
        ErrorEnum errorEnum = ErrorEnum.getByCode(errorCode);
        attributes.put("message",errorEnum.message);
        attributes.put("code",errorEnum.code);
        return new ResponseEntity(attributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
