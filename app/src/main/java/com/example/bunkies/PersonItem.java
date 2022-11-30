package com.example.bunkies;

public class PersonItem {
    String name;
    boolean onTask;

    public PersonItem(String name, boolean onTask){
        this.name = name;
        this.onTask = onTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnTask() {
        return onTask;
    }

    public void setOnTask(boolean onTask) {
        this.onTask = onTask;
    }
}
