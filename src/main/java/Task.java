public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        //this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String markAsDone() {
        this.isDone = true;
        return "X";
    }

    public String unmarkAsDone() {
        this.isDone = false;
        return " ";
    }

    //...
}