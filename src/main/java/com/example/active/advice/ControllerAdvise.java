package com.example.active.advice;

import com.example.active.dto.ApiResponse;
import com.example.active.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//MethodArgumentNotValidException
@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity apiException(ApiException apiException){
        return ResponseEntity.status(400).body(new ApiResponse(apiException.getMessage(),400));
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String message = methodArgumentNotValidException.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message,400));
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception exception){
        String message = exception.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message,400));
    }
}
