package duke.tasks;

import java.util.Hashtable;
import duke.exceptions.*;

import static duke.exceptions.UserInputException.inputExceptionType.EMPTY_TASK_DESCRIPTION;

//initial skeleton adapted from https://nus-cs2113-ay2223s2.github.io/website/schedule/week3/project.html partial solution
public abstract class Task {
    String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task() {
    }
    public String getDescription(){
        return description;
    }

    public void toggleDone(){
        isDone = !isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    @Override
    public String toString(){
        return getStatusIcon() + " " + description;
    }
}