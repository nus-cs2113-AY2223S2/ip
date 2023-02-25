package duke.tasklist.task;

import duke.tasklist.task.Task;

/**
 * Represents tasks without any date/time attached to it.
 * e.g., visit new theme park.
 */
public class Todo extends Task {
    /**
     * Constructor initializing the content of the task.
     * The task is unmarked by default.
     * @param content content of the task.
     */
    public Todo(String content) {
        this(content, false);
    }

    public Todo(String content, boolean isMarked) {
        super(content, isMarked);
    }

    /**
     * Constructs a Todo class from arguments
     * @param args arguments containing the content of the todo task.
     * @throws IllegalArgumentException exceptions with message when (part of) input is missing.
     */
    public Todo(String[] args) throws IllegalArgumentException {
        assert args[0].equals("todo");
        StringBuilder content = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            content.append(args[i]).append(" ");
        }
        if (content.toString().isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.content = content.toString().trim();
    }

    @Override
    public String toCSV() {
        return "todo," +
                super.toCSV();
    }

    /**
     * Converts the class to a string with label and marked status.
     * @return a string containing the task's label and marked status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return super.equals(obj);
        }
    }
}
