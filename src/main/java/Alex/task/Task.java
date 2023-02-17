package Alex.task;

public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone(){
        isDone = true;
    }
    public String getDescription(){
        return this.description;
    }
    public void unmark(){
        isDone = false;
    }

    public String getType() {
        return type;
    };

    public boolean getDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
