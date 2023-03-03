package task;

public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task done based on index of task.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Prints to CLI to show that the mark command has been done successfully.
     */
    public void printMarkMessage() {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * Marks task undone based on index of task.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Prints to CLI to show that the unmark command has been done successfully.
     */
    public void printUnmarkMessage() {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(this);
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public String saveStatusIcon() {
        return (isDone ? "X" : "O");
    }

    public String printToFile() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

