package com.phrase.demo.errorhandling;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericError {
    private HttpStatus status;
    private String message;
    private int status_code;

    public GenericError(HttpStatus status,String status_code,String message){
        this.status = status;
        this.status_code = Integer.parseInt(status_code);
        this.message = message;
    }
}
