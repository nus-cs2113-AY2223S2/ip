package Arsdorint.task;

public class Task {
    public static int numOfTasks = 0;
    public String description;
    public boolean isDone;
    public String taskType = "[ ]";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
    }

    public String getStatus() {
        return (isDone) ? "[X]" : "[ ]";
    }
    public void printTask() {
        System.out.println(this.taskType +
                this.getStatus() + " " + this.description);
    }
}
