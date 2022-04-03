package com.home.test;

public class Applicant {
    public String user;
    public String date;
    public String time;
    public String title;
    public String designer;
    public String description;
    public String price;
    public String approved;
    public String atendees;

    public Applicant(){
        title = null; designer = null;
        description = null; price = null; approved = null;
        user = null;
        date = null;
        time = null; atendees = null;
    }

    public Applicant(String u, String da, String ti,String t, String d, String desc, String p, String a, String at){
        this.title = t; this.description = desc; this.designer= d;
        this.price = p; this.approved = a;
        this.user = u; this.date = da; this.time = ti; this.atendees = at;
    }

    public String getDate() {
        return date;
    }

    public String getAtendees() {
        return atendees;
    }

    public void setAtendees(String atendees) {
        this.atendees = atendees;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getTitle() {
        return title;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
