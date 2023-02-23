package duke.command;

import duke.task.Task;

public class Todo extends Task {
    protected String add;

    public Todo(String taskDescription) {
        super(taskDescription);
        setCommand("todo " + taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
