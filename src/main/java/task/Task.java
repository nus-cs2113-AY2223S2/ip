package task;

import java.lang.NullPointerException;

public class Task{
    protected String description;
    protected boolean mark;

    public Task(String description) throws EmptyDescriptionException {
        setDescription(description);
        mark = false;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String newDescription) throws EmptyDescriptionException {
        if (newDescription == null || newDescription.isEmpty()) {
            throw new EmptyDescriptionException("Description given is empty!", new NullPointerException());
        }
        description = newDescription;
    }
    @Override
    public String toString() {
        return String.format("[%c] %s", getStatusIcon(), description);
    }
    public char getStatusIcon() {
        // mark done task with X
        return (mark ? 'X' : ' ');
    }
    public boolean isMark() {
        return mark;
    }
    public void setMark(boolean newMark) {
        this.mark = newMark;
    }
}
