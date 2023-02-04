package duke.task;
import duke.exception.IllegalCommandException;

public class Task {
    private String description;
    private boolean isDone;
    public Task(){
        this.isDone=false;
        this.description="";
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {

        return (isDone() ? "X" : " "); // mark done task with X
    }

    //...

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) throws IllegalCommandException {
        if(isDone==done){
            throw new IllegalCommandException("No change in done state");
        }
        isDone = done;
    }
    @Override
    public String toString(){
        return description;
    }
}
