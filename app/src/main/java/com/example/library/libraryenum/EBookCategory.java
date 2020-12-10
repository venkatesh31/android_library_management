package com.example.library.libraryenum;

public enum EBookCategory {
    HORROR("Horror"), Romance("Romance"), Comedy("Comedy");

    private final String code;

    EBookCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
