public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void undo(){
        this.isDone = false;
    }

    public String isDone(){
        return (isDone ? "[X] " : "[ ] ");
    }
}
