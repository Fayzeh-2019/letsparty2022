package com.home.test;

public class Design {
    public String title;
    public String designer;
    public String description;
    public String price;
    public String approved;
    public String offer;

    public Design(){
        title = null; designer = null;
        description = null; price = null; approved = null; offer = null;
    }

    public Design(String t, String d, String desc, String p, String a, String o){
        this.title = t; this.description = desc; this.designer= d;
        this.price = p; this.approved = a; this.offer = o;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
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
