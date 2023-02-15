package duke;
import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone = false;
    public static ArrayList<Task> tasks = new ArrayList<>(); //keep a collection of tasks
    public static int taskCount = 0; //keep track of number of tasks

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X, leave empty if not done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }
}

