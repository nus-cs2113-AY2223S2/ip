public class Task {

    private int id;
    private String taskInfo;
    private boolean isDone;

    private static int numberOfTasks = 0;

    public Task(String taskInfo) {
        setTaskInfo(taskInfo);
        numberOfTasks++;
        setId(numberOfTasks);
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }
    
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

}
