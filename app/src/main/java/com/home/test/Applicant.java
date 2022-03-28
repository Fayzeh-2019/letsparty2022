package com.home.test;

public class Applicant extends Design{
    String user;

    public Applicant(){
        super();
        user = null;
    }

    public Applicant(String u){
        super();
        this.user = u;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
