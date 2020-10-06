package com.aqua.data;

import java.net.URL;

public class User {
    private int id;
    private String token;
    private String name;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getID(){
        return this.id;
    }
    public String getName(){return this.name;}
    public URL getProfileUrl() {return null;
    }
}
