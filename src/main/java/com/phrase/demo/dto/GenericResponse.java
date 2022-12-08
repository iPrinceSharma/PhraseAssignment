package com.phrase.demo.dto;

import com.phrase.demo.errorhandling.GenericError;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class GenericResponse {
    private String status;
    private String message;
    private int statusCode;
    private Object data;

    public GenericResponse(String status,String message,int statusCode,Object data){
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }
}
