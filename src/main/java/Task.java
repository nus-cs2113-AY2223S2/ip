public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        this.printDescWithStatus();
    }
    public void markUndone() {
        this.isDone = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        this.printDescWithStatus();
    }
    public String getDescription() {
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void printDescWithStatus() {
        System.out.println("[" + this.getStatusIcon() + "] " + this.getDescription());
    }
}

