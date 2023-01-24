public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(){
        taskDescription = null;
        isDone = false;
    }

    public Task (String description){
        this.taskDescription = description;
        this.isDone = false;
    }

    public String getTaskName(){
        return taskDescription;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
}