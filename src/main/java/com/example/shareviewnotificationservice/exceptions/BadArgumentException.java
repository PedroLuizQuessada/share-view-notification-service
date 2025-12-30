package com.example.shareviewnotificationservice.exceptions;

public class BadArgumentException extends BadRequestException {
    public BadArgumentException(String message) {
        super(message);
    }
}
