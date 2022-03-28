package com.home.test;

public class Design {
    String title, designer, description, price;

    public Design(){
        title = null; designer = null;
        description = null; price = null;
    }

    public Design(String t, String d, String desc, String p){
        this.title = t; this.description = desc; this.designer= d;
        this.price = p;
    }

    public String getTitle() {
        return title;
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
