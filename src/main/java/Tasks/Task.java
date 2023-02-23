package Tasks;

public class Task {

    protected String description;
    protected boolean isDone;

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }


    // class initialization
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // class modifiers
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    // returning status icon
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return '[' + getStatusIcon() + "] "+ description + " ";
    }
}
