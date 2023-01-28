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
        return this.description;
    }

    public boolean getStatus(){
        return this.isDone;
    }

    public String getStatusIcon(){
        return (isDone? "[X]":"[ ]");
    }


}
