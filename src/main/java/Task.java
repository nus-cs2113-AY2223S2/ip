public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {   //ok to leave as public?
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getTypeIcon() {
        return "NULL";
    }
    public String taskTypeIcon() { return "[" + getTypeIcon() + "]";}
    public void setDescription(String description) {
        this.description = description;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public String isDoneIcon() { return "[" + getStatusIcon() + "]";}
    public String getDescription() {
        return description;
    }

    public String getTask() {
        return taskTypeIcon() + isDoneIcon() + " " + getDescription();
    }

}
