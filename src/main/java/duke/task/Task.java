package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected String taskString;
    public static final String COMMA = " , ";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskString = saveTaskString();
    }

    public String toString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    public void setDone(String mark) {
        if (mark.equals("mark")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public abstract String saveTaskString();

    public String addTaskMessage() {
        return "Got it. I've added this task:\n  " + this.toString() + System.lineSeparator();
    }

    public String getTaskString() {
        return taskString;
    }
}
