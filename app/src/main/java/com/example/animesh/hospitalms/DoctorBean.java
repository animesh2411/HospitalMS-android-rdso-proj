package com.example.animesh.hospitalms;

/**
 * Created by animesh on 7/5/2017.
 */

public class DoctorBean {
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

    String name;
    int ID;
}
