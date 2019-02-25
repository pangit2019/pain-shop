package com.hbc.demo.utils;

public enum ColorType {

    GLOSS("G"), MATTE("M"), NONE("N");

    private final String color;

    public static ColorType fromString(String value) {
        for (ColorType c : ColorType.values()) {
            if (c.color.equalsIgnoreCase(value)) {
                return c;
            }
        }
        return null;
    }

    ColorType(String color) {
        this.color = color;
    }
}
