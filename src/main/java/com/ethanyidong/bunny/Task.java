package com.ethanyidong.bunny;

public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    protected abstract String label();

    @Override
    public String toString() {
        char marker = ' ';
        if (this.done) {
            marker = 'X';
        }
        return "[" + this.label() + "][" + marker + "] " + this.getName();
    }
}
