package com.itheima.controller;

import com.itheima.dto.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler // status = 200
//    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public R handler401(Exception401 e) {
        return R.error(401, e.getMessage());
    }
}
