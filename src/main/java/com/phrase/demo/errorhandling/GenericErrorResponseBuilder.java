package com.phrase.demo.errorhandling;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonPropertyOrder({"code", "message"})

public class GenericErrorResponseBuilder implements Serializable {
    public GenericErrorResponseBuilder() {
    }

    public static GenericErrorResponseBuilder.GenericErrorResponse buildErrorObject() {
        return new GenericErrorResponseBuilder.GenericErrorResponse();
    }

    public static class GenericErrorResponse implements Serializable {
        private HttpStatus code;
        private String message;
        private String status_code;


        public GenericErrorResponse withCode(HttpStatus code) {
            this.code = code;
            return this;
        }

        public GenericErrorResponse withMessage(String message) {
            this.message = message;
            return this;
        }

        public GenericErrorResponse withStatusCode(String status_code) {
            this.status_code = status_code;
            return this;
        }

        public GenericError build() {
            return new GenericError(code, status_code, message);
        }
    }

}