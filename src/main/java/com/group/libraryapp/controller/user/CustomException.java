package com.group.libraryapp.controller.user;

import org.checkerframework.checker.nullness.qual.Nullable;

public class CustomException extends Exception {
    public CustomException(String message, @Nullable Throwable cause) {
        super(message, cause);  // cause에 원인 예외를 전달
    }
}
