package com.example.library.libraryenum;

public enum EUserType {
    ADMIN(1), USER(2);

    private final Integer code;

    EUserType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}