package com.example.game.exception;

import com.example.game.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleException(Exception ex) {
        // 这里只是一个示例，你可能需要根据不同的异常类型返回不同的状态码或错误信息
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(500, ex.getMessage(), null));
    }
}
