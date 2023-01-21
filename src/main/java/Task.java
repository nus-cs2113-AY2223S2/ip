public class Task {
    protected String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String setAsDone(){
        this.isDone = true;
        return this.task;
    }
    public String setAsUndone(){
        this.isDone = false;
        return this.task;
    }

    public String getStatusIcon() {
        return (this.isDone ? "✔" : " ");
    }

    public String getTask() {
        return task;
    }
}
