package com.phrase.demo.errorhandling;

public class ProjectRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 6732594064350338873L;
    private String code;

    public ProjectRuntimeException(String message) {
        super(message);
    }

    public ProjectRuntimeException(String code, String message) {
        this(message);
        this.code = code;
    }

    public ProjectRuntimeException(String message, Throwable e) {
        super(message, e);
    }
    public String getCode() {
        return this.code;
    }

}