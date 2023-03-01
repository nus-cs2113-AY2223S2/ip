package com.ethanyidong.bunny.task;

/**
 * Represents a minimal implementation of a <code>Task</code> with only a name and doneness state.
 */
public class Todo extends Task {
    /**
     * Create a todo with a name.
     * The new todo will be not done by default.
     * @param name the name of the todo to create
     */
    public Todo(String name) {
        super(name);
    }

    protected String label() {
        return "T";
    }
}
