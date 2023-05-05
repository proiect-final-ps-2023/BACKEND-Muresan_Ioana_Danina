package com.proiectps.shopping.model;

public class Ad {
    String name;
    String description;
    boolean increased;

    public Ad(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public boolean getIncreased() {
        return increased;
    }

    public void setIncreased(boolean increased) {
        this.increased = increased;
    }

    public String getDescription() {
        return description;
    }
}
