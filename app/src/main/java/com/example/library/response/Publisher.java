package com.example.library.response;

import java.io.Serializable;

public class Publisher implements Serializable {
    private Integer publisherId;
    private String name;

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
