package com.example.diary_sample.feature;

import com.example.diary_sample.global.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 실제 응답에 들어가는 httpStatus
    @ExceptionHandler(BindException.class)
    public Response<Object> bindException(BindException e) {
        return Response.result(
                "",
                HttpStatus.BAD_REQUEST,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
    }

}
