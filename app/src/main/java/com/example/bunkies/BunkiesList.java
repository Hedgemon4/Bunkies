package com.example.bunkies;

import java.io.Serializable;

public class BunkiesList implements Serializable {
    private String listName;
    private String[] people;
    private String listFile;

    public BunkiesList(String listName, String[] people) {
        this.listName = listName;
        this.people = people;
    }

    public BunkiesList(String listName){
        this.listName = listName;
        this.people = new String[]{"Emily"};
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String[] getPeople() {
        return people;
    }

    public void setPeople(String[] people) {
        this.people = people;
    }

    public String getListFile() {
        return listFile;
    }

    public void setListFile(String listFile) {
        this.listFile = listFile;
    }
}
