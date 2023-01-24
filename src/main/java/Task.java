public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");    //mark task with X or tick
    }

    public void markDone(){
        this.isDone = true;     //mark task as done
    }

    public void markUndone(){
        this.isDone = false;    //mark as undone
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;    //string of status and description
    }
}
