package com.example.bfhl.exception;

import com.example.bfhl.dto.BfhlResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponseDto> handleException(Exception ex) {
        BfhlResponseDto response = BfhlResponseDto.builder()
                .isSuccess(false)
                .userId(null)
                .email(null)
                .rollNumber(null)
                .oddNumbers(Collections.emptyList())
                .evenNumbers(Collections.emptyList())
                .alphabets(Collections.emptyList())
                .specialCharacters(Collections.emptyList())
                .sum(null)
                .concatString(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
