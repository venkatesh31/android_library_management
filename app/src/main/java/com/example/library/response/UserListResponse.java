package com.example.library.response;

import java.io.Serializable;
import java.util.List;

public class UserListResponse implements Serializable {
    private List<User> data;


    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}