package com.home.test;

import com.google.type.DateTime;

import java.io.Serializable;

public class Message implements Serializable {

    String name;
    String to;
    String message;
    java.util.Date dateTime;

    public Message(){
        name = null; message = null; to = null; dateTime =null;
    }

    public Message(String s, String m, String to , java.util.Date dateTime){
        name= s; message=m; to = this.to; dateTime= this.dateTime;
    }

    public String getTo() {
        return to;
    }

    public java.util.Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.util.Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
