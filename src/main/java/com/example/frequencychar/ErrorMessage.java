package com.example.frequencychar;

import java.util.Date;

public class ErrorMessage {
    private int status;
    private String message;
    private Date date;

    public ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
        this.date = new Date();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
