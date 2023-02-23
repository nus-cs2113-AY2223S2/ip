package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public String printTask(){
        return ("  " + "[" + getStatusIcon() + "]" + " " + getDescription());
    }

    public String checkCompletion() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String saveTask() {
        return "";
    }
}
