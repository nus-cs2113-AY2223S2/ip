package tasks;

import dukeException.DukeException;

public class Task {
    private String description;
    private boolean isMark;
    public Task(String inDescription, boolean isMark) throws DukeException {
        if (inDescription.length() == 0) {
            throw new DukeException();
        }
        this.description = inDescription;
        this.isMark = isMark;
    }
    public void mark() {
        this.isMark = true;
    }
    public void unMark() {
        this.isMark = false;
    }
    public String getMarked() {
        if (this.isMark) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.getMarked() + " " + this.description;
    }
}
