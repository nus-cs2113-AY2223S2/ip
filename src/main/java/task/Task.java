package task;

/**
 * Object of a normal task, includes: a description.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
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

    public int getIntStatus(){
        return isDone? 1 : 0 ;
    }
    public String getStatusIcon(){
        return (isDone? "[X]":"[ ]");
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + getDescription();
    }

    public String getLetter(){
        return "A";
    }
}
