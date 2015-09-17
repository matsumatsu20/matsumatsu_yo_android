package com.mikazuki.android.matsumatsuyo.domain.entity;

/**
 * Created by matsuMac on 2015/09/13.
 */
public class Message {
    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Message(String message) {

        this.Message = message;
    }
}
