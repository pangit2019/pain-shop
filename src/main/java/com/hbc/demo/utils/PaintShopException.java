package com.hbc.demo.utils;

public class PaintShopException extends Exception {

    public PaintShopException(String message) {
        super(message);
    }

    public PaintShopException(String message, Throwable cause) {
        super(message, cause);
    }
}
