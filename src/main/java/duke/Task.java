package duke;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // mark done task with X on 2nd []
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    // For marking and unmarking tasks
    public void setDone(boolean done) {
        isDone = done;
    }
    // Return status of task
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString(){

        return  getStatusIcon()  + description;
    }


}
