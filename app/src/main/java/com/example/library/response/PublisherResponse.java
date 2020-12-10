package com.example.library.response;

import java.io.Serializable;
import java.util.List;

public class PublisherResponse implements Serializable {
    private List<Publisher> data;

    public List<Publisher> getData() {
        return data;
    }

    public void setData(List<Publisher> data) {
        this.data = data;
    }
}