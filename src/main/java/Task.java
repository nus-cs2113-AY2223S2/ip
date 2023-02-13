public class Task {

    protected String description;
    protected boolean isDone;

    public String getTask(){
        return this.description;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String printTask() {
        return ("[" + this.getTaskIcon() + "] " + "[" + this.getStatusIcon() + "] " + this.getTask() + " ");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }
    public String getTaskIcon() {
        return " ";
    }
    public void MarkStatusDone() {

        this.isDone = true;
    }
    public void MarkStatusUndone() {
        this.isDone = false;
    }
    public String getBy() {
        return " ";
    }
    public String getTo() {
        return " ";
    }
    public String getFrom() {
        return " ";
    }
}