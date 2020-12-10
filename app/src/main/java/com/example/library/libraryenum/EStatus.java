package com.example.library.libraryenum;

public enum EStatus{
    ACTIVE(1), INACTIVE(0);

    private final Integer code;

    EStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}