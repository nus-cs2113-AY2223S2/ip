package dude.task;
public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done dude.task with X
    }

    public void setDone(){
        this.isDone = true;
    }

    public void setUndone(){
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}

