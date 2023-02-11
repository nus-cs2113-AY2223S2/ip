package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description){
        this.description = description;
        return;
    }

    public void setStatus(boolean status){
        this.isDone = status;
        return;
    }

    public String getDescription(){
        return description;
    }

    public boolean getStatus(){
        return isDone;
    }

    public String getStatusIcon(){
        return (isDone? "[X]":"[ ]");
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + getDescription();
    }

}
