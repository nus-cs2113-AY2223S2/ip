package com.ethanyidong.bunny.task;

import com.ethanyidong.bunny.task.Task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    protected String label() {
        return "T";
    }
}
