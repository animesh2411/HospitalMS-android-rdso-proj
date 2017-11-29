package com.example.animesh.hospitalms;

/**
 * Created by animesh on 7/5/2017.
 */

public class ReceptionBean {
    int ID; String name;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    String pass;

    public String toString(){
        return "Name: "+name+"\nID: "+Integer.parseInt(String.valueOf(ID));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
