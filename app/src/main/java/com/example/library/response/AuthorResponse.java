package com.example.library.response;

import java.io.Serializable;
import java.util.List;

public class AuthorResponse implements Serializable {
    private List<Author> data;

    public List<Author> getData() {
        return data;
    }

    public void setData(List<Author> data) {
        this.data = data;
    }
}