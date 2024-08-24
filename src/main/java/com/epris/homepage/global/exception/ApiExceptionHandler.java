package com.epris.homepage.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("[handleCustomException] {} : {}",e.getErrorCode().name(), e.getErrorCode().getMessage());
        return ErrorResponse.error(e);
    }

    /* @Valid 유효성 체크 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e){
        log.warn("MethodArgumentNotValidException 발생!!!  trace:{}", e.getStackTrace());
        return ErrorResponse.error(new CustomException(ErrorCode.INPUT_IS_NULL));
    }
}