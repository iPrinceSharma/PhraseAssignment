package com.phrase.demo.errorhandling;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler {

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<GenericError> commonHandler(ProjectRuntimeException exception) {
        ResponseEntity<GenericError> response = null;
        switch (exception.getCode()){
            case "204": response = build(NO_CONTENT,exception.getMessage(),exception.getCode());
                        break;
            case "400":response = build(BAD_REQUEST,exception.getMessage(),exception.getCode());
                        break;
            case "401":response = build(UNAUTHORIZED,exception.getMessage(),exception.getCode());
                        break;
            case "500":response = build(INTERNAL_SERVER_ERROR,exception.getMessage(),exception.getCode());
                        break;
            case "503":response = build(SERVICE_UNAVAILABLE,exception.getMessage(),exception.getCode());
                        break;
            default: response = build(INTERNAL_SERVER_ERROR,exception.getMessage(),exception.getCode());
        }
        return response;
    }

    public static ResponseEntity<GenericError> build(HttpStatus status, String message, String code) {
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON)
                .body(GenericErrorResponseBuilder.buildErrorObject()
                        .withCode(status)
                        .withStatusCode(code)
                        .withMessage(message)
                        .build());
    }
}
