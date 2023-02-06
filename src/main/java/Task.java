public class Task {

    private int id;
    private String taskInfo;
    private boolean isDone;
    private String taskType = " ";

    private static int numberOfTasks = 0;

    public Task(String taskInfo, String taskType) {
        setTaskInfo(taskInfo);
        numberOfTasks++;
        setId(numberOfTasks);
        this.isDone = false;
        setTaskType(taskType);
    }

    public void markAsDone() {
        if (isDone) {
            System.out.println("Task was already marked as done!");
        } else {
            isDone = true;
            System.out.println("Good job! I've marked it as done!");
            System.out.println(this.getStatus() + this.taskInfo);
        }
    }

    public void markAsUndone() {
        if (!isDone) {
            System.out.println("Task was already marked as undone!");
        } else {
            isDone = false;
            System.out.println("Alright, I've marked the task as undone.");
            System.out.println(this.getStatus() + this.taskInfo);
        }
    }

    public String getStatus() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
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

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return String.format("[%s]", taskType);
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

}
