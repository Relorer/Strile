package com.example.strile.service.structures;

public class Subtask {
    private final String name;
    private final boolean complete;

    public Subtask(String name, boolean complete) {
        this.name = name;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return complete;
    }

    public Subtask setState(boolean state) {
        return new Subtask(name, state);
    }
}
