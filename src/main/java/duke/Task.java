package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getStatusIconSave() {
        return (isDone ? "1" : "0");
    }
    public String getDescription(){
        return description;
    }

    public String getType(){ return " ";}
    public String getPeriod(){
        return "";
    }
    public String getPeriodSave() { return "";}
    public String getDeadline(){ return "";}

    public String getDeadlineSave(){ return "";}
    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + description;
    }
}