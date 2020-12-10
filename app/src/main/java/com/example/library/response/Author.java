package com.example.library.response;

import java.io.Serializable;

public class Author implements Serializable {
    private Integer authorId;
    private String name;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
