package com.example.library.response;

import java.io.Serializable;
import java.util.List;

public class UserBookResponse implements Serializable {
    private List<UserBook> data;

    public List<UserBook> getData() {
        return data;
    }

    public void setData(List<UserBook> data) {
        this.data = data;
    }
}