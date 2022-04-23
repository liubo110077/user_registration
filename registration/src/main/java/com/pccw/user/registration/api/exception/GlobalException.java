package com.pccw.user.registration.api.exception;

import com.pccw.user.registration.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseEntity handleIllegalArgumentException(Exception e){
       log.error("exception is",e);
       return ResponseEntity.ok(Response.error500(e.getMessage()));
    }

}