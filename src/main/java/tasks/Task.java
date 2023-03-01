package tasks;

public class Task {
    public String description;
    public boolean done;

    public Task(String descriptionInput) {
        this.description = descriptionInput;
        this.done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

}
