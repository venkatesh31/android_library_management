package com.example.library.response;

import java.io.Serializable;
import java.util.List;

public class BookResponse implements Serializable {
    private List<Book> data;

    public List<Book> getData() {
        return data;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }
}