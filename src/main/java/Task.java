//Create a task class
public class Task {
    protected String taskName;
    protected boolean isDone;
    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }
    //method to mark as done
    public void markAsDone(){
        this.isDone = true;
    }
    //method to mark as not done
    public void markAsNotDone(){
        this.isDone = false;
    }
    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone){
            return "[X] " + this.taskName;
        }else{
            return "[ ] " + this.taskName;
        }
    }
}

