package duke.command;

import duke.task.Task;

public class Todo extends Task {
    protected String add;

    /**
     * Initialises the todo task from the user command.
     *
     * @param taskDescription String of description of task to be added.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
        setCommand("todo " + taskDescription);
    }

    /**
     * Makes a format of string to be printed.
     *
     * @return String of deadline with format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
