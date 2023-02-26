package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;


    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
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

    public String toString(){

        return String.format("[%s][%s] %s", this.type.getAbbreviation(),
                this.getStatusIcon(), this.description);
    }


    public String saveText() {
        return String.format("%s | %d | %s", this.type.getAbbreviation(),
                this.isDone ? 1 : 0, this.description);
    }

}
