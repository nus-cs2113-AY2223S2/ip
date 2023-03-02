package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    String LINE = "────────────────────────────────────────────────────────────────────────\n";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    @Override
    public String toString() {
        return taskStatus() + description;
    }
    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public void markAsDone() {
        setDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as DONE:");
        System.out.println("  " + this.toString());
        System.out.println(LINE);
    }

    public void markAsUndone() {
        setUndone();
        System.out.println(LINE);
        System.out.println("OK! I've marked this task as NOT DONE YET:");
        System.out.println("  " + this.toString());
        System.out.println(LINE);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String taskStatus() {
        return ("[" + getStatusIcon() + "] ");
    }
}
