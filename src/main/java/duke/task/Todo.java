package duke.task;

public class Todo extends Task {

    protected final String taskName;

    public Todo(String description) {
        super(description);
        this.taskName = description;
    }

    /**
     * returns the task stored as a string format to be
     * saved in the save file
     *
     * @return returns the task in the format of user commane
     */
    public String returnCommand() {
        return super.completed() + "todo " + taskName;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
