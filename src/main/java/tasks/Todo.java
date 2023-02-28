package tasks;

import tasks.Task;

public class Todo extends Task {

    /**
     * Constructor method for the ToDo object.
     * @param description This is the description of the ToDo taskk.
     */
    public Todo(String userInput, String description) {
        super(userInput, description);
    }

    /**
     * Returns the details of the ToDo task in a specific format.
     * @return String This returns the details of the ToDo Task.
     */
    public String toString() {
        return ".[T]" + super.toString();
    }
}
