package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;


    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }
    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }
    // mark done task with X on 2nd []
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // For marking and unmarking tasks
    public void setDone(boolean done) {
        isDone = done;
    }
    // Return status of task
    public boolean isDone() {
        return isDone;
    }

    public String toString(){

        return  "[" + getStatusIcon() + "]"  + description;
    }
    public String saveText(){
        return "";
    }

}
