package duke.tasks;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String type;
    protected String start;
    protected String end;

    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void printTask() {
        System.out.println("task");
    }
}
