package com.example.library.response;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable {
    private User recordinfo;

    public User getRecordinfo() {
        return recordinfo;
    }

    public void setRecordinfo(User recordinfo) {
        this.recordinfo = recordinfo;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "recordinfo=" + recordinfo +
                '}';
    }
}