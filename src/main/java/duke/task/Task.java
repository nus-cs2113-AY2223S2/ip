package duke.task;

import duke.main.Storage;

import java.util.StringJoiner;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String toSaveString(String... taskParameters) {
        StringJoiner saveString = new StringJoiner(Storage.DELIMITER);
        for (String string : taskParameters) {
            saveString.add(string);
        }
        return saveString.toString();
    }
}
