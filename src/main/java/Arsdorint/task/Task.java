package Arsdorint.task;

public class Task {
    public static final String VERTICAL_BAR = " | ";
    public static int numOfTasks = 0;
    public String description;
    public boolean isDone;
    public String taskType = "[ ]";
    public String taskName;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
    }

    public String toString() {
        return (this.taskType + this.getStatus() + " " + this.description);
    }

    public String getStatus() {
        return (isDone) ? "[X]" : "[ ]";
    }
    public void printTask() {
        System.out.println(this.toString());
    }
    public int binaryRes() {
        return (isDone) ? 1 : 0;
    }
    public String toSave() {
        return (this.taskName + VERTICAL_BAR + binaryRes() + VERTICAL_BAR + this.description + "\n");
    }
}
