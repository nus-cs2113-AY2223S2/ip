package Entities;

/**
 * Basic task
 */
public class Todo extends Task {
    /**
     * Constructor for Todo Class
     * @param taskDescription details of task
     * @param isDone true if task is completed, otherwise false
     */
    public Todo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    /**
     * Defines how Todo should be printed
     * @return string format of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
