public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;
    private static String line = "__________________________________________________________";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "0";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return (type);
    }

    public String toString() {
        return ("[" + type + "][" + getStatusIcon() + "] " + description);
    }

    public void markAsDone(String action) {
        if (action.startsWith("unmark")) {
            this.isDone = false;
            System.out.println("Ok, I've marked this task as not done yet:");
        } else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println("  [" + getStatusIcon() + "] " + description);
    }

}

