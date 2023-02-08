package duke.addable;
import duke.exception.ArgumentBlankException;

public class Task {
    private String description;
    private boolean isDone;
    protected final String commandString = "task";

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) throws ArgumentBlankException {
        if (description.isBlank()) {
            throw new ArgumentBlankException(this.getCommandString(), "description");
        }
        this.setDescription(description);
        this.isDone = false;
    }

    public String getCommandString() {
        return commandString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
