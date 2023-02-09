package tasks;

public class Task {
    private String description;
    private boolean isDone;
    private int taskNumber;
    private static int numberOfTasks = 0;

    public Task(String description){
        numberOfTasks += 1;
        this.description = description;
        this.isDone = false;
        this.taskNumber = numberOfTasks;
    }
    public void markDone(){
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "");
    }

    public String getDescription(){
        return description;
    }

    public int getTaskNumber(){
        return taskNumber;
    }

    public static int getNumberOfTasks(){
        return numberOfTasks;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
