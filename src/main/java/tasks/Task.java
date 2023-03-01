package tasks;

/**
* Creates a Task, which by default has a description and a marker indicating whether it is completed
* */
public class Task {
    private String description;
    private boolean done;

    /**
     * Factory function for task
     * */
    public Task(String descriptionInput) {
        this.description = descriptionInput;
        this.done = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return done;
    }

    public void markThisTask(boolean mark) {
        done = mark;
    }

    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

}
