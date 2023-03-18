package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /* change data to a file format in order to store in a file */
    @Override
    public String toFileFormat() {
        return "T | " + isMarked + " | " + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}
