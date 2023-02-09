package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    String lineBreaker = "____________________________________________________________\n";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    @Override
    public String toString() {
        return taskStatus() + description;
    }
    public void markAsDone() {
        this.isDone = true;
        System.out.println(lineBreaker);
        System.out.println("Nice! I've marked this duke.task as DONE:");
        System.out.println("  " + this.toString());
        System.out.println(lineBreaker);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println(lineBreaker);
        System.out.println("OK! I've marked this duke.task as NOT DONE YET:");
        System.out.println("  " + this.toString());
        System.out.println(lineBreaker);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String taskStatus() {
        return ("[" + getStatusIcon() + "] ");
    }
}
