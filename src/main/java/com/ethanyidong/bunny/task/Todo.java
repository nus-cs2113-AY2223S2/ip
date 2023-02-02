package com.ethanyidong.bunny.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    protected String label() {
        return "T";
    }
}
