package duke;

public class Todo extends Task{
    protected boolean isDone;
    public Todo(String description) {
        super(description);
        this.isDone = false;

    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    public String getTypeIcon() {
        return ("[T]"); // mark done task with X
    }
    public String getInfo() {
        return "";
    }
    public void setDone(boolean d) {
        this.isDone = d;
    }
}
