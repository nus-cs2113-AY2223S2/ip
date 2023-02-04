
//initial skeleton adapted from https://nus-cs2113-ay2223s2.github.io/website/schedule/week3/project.html partial solution
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
}