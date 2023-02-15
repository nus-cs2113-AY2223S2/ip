package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String line){
        this.description = line;
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

    public String getDesc(){
        return description;
    }

    @Override
    public String toString(){
        return description;
    }
}
