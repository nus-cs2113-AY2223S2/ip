public class Task {
    protected String description;
    protected static int taskCount = 0;
    protected int taskID;
    protected boolean isDone = false;


    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public Task(String description, int taskID) {
        this.description = description;
        this.taskID = taskID;
        taskCount += 1;
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

    public void printTask() {
        if(isDone){
            System.out.println((taskID + 1) + ".[ ] " +  description);
        }
        else{
            System.out.println((taskID + 1) + ".[X] " +  description);
        }
    }
}