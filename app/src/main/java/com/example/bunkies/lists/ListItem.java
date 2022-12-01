package com.example.bunkies.lists;

import java.io.Serializable;

public class ListItem implements Serializable {
    private String text;
    private String description;
    private boolean done;
    private boolean[] people;

    public ListItem(String text, boolean[] people, boolean done) {
        this.text = text;
        this.done = done;
        this.people = people;
        this.description = "";
    }

    public ListItem(String text, String description, boolean[] people, boolean done) {
        this.text = text;
        this.description = description;
        this.people = people;
        this.done = done;
    }

    public ListItem(String text) {
        this.text = text;
        this.description = "";
        this.people = new boolean[]{false, false, false, false};
        this.done = false;
    }

    public ListItem(String text, boolean done) {
        this.text = text;
        this.done = done;
        this.description = "";
        this.people = new boolean[0];
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

    public boolean[] getPeople() {
        return people;
    }

    public void setPeople(boolean[] people) {
        this.people = people;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
