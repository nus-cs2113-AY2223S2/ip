public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        printDescWithStatus();
    }

    public void markUndone() {
        isDone = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        printDescWithStatus();
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void printDescWithStatus() {
        System.out.println("[" + getStatusIcon() + "] " + getDescription());
    }
}

