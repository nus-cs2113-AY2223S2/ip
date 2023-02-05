public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() throws DukeAlreadyMarkedException {
        if (this.isDone) {
            throw new DukeAlreadyMarkedException();
        }
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    public void markAsNotDone() throws DukeAlreadyMarkedException {
        if (!this.isDone) {
            throw new DukeAlreadyMarkedException();
        }
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}