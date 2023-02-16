package keqing.tasks;

public class Task {
    protected String description;
    protected static int taskCount = 0;
    protected int taskID;
    protected boolean isDone = false;


    public void setDone() {
        isDone = true;
    }

    public void seUndone() {
        isDone = false;
    }

    public Task(String description, int taskID) {
        this.description = description;
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public int getID() {
        return taskID;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static void setTaskCount(int count) {
        taskCount = count;
    }

    public String toString() {
        if(isDone){
            return "[X] " +  description;
        }
        else{
            return "[ ] " +  description;
        }
    }
}