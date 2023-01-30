public class Task {
    private String description;
    private boolean isDone;
    private static int total;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        total++;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setMark(boolean done) {
        this.isDone = done;
    }


    //...
}

