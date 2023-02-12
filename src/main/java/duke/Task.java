package duke;

public class Task {
    public static final String DONE_ICON = "X";
    public static final String NOT_DONE_ICON = " ";
    protected String description;
    protected boolean isDone;
    private static int numberOfTasks = 0;

    public int getNumberOfTasks() {
        return numberOfTasks;
    }


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public void remove() {
        numberOfTasks--;
    }

    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON); // mark done task with X
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String saveFormat() {
        String toSave = "0";
        if (isDone) {
            toSave = "1";
        }
        return toSave + "|" + description;
    }
}
