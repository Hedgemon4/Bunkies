package com.example.bunkies;

import java.util.ArrayList;
import java.util.Arrays;

public class ListItem {
    private String text;
    private boolean done;
    private ArrayList<String> people = new ArrayList<>();

    public ListItem(String text, boolean done, String[] people){
        this.text = text;
        this.done = done;
        this.people.addAll(Arrays.asList(people));
    }

    public ListItem(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ArrayList<String> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<String> people) {
        this.people = people;
    }
}
