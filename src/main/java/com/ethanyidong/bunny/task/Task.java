package com.ethanyidong.bunny.task;

/**
 * Represents a task in the Bunny task list.
 * All tasks minimally have a name and a doneness state.
 * Inheritors may implement specific features such as deadlines.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Create a task with a name.
     * The new task will be not done by default.
     * @param name the name of the task to create
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Flags the task as done or not done
     * @param isDone the new doneness state of the task
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * @return a label which will be displayed in the <code>toString()</code> representation of the <code>Task</code>
     */
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
