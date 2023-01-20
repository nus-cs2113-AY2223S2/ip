public class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected int taskNumber;


    public Task(String description){
        this.taskName = description;
        this.isCompleted = false;
    }

    public String getStatusIcon(){
        return (isCompleted ? "[X]" : "[ ]");
    }

    public void markAsDone(){
        this.isCompleted = true;
    }
    public void markAsNotDone(){
        this.isCompleted = false;
    }
}
