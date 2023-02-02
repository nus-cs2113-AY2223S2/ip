package com.ethanyidong.bunny.task;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected abstract String label();

    @Override
    public String toString() {
        char marker = ' ';
        if (this.isDone) {
            marker = 'X';
        }
        return "[" + this.label() + "][" + marker + "] " + this.getName();
    }
}
