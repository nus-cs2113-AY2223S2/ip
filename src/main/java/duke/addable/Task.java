package duke.addable;
import duke.exception.ArgumentBlankException;

public abstract class Task {
    private String description;
    private boolean isDone;
    protected final String commandString = "task";

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description, boolean isDone) throws ArgumentBlankException {
        if (description.isBlank()) {
            throw new ArgumentBlankException(this.getCommandString(), "description");
        }
        this.setDescription(description);
        this.isDone = isDone;
    }

    public String getCommandString() {
        return commandString;
    }

    public String getLetter() {
        return "A";
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
    public abstract String[] getExtraArguments();
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
