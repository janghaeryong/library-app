package com.group.libraryapp.controller.user;

public class ErrorResponse2 {
    private String message;
    private String method;
    private String details;
    private int code;

    public ErrorResponse2(String message, String method, String details, int code) {
        this.message = message;
        this.method = method;
        this.details = details;
        this.code = code;
    }

    // Getter와 Setter
}
