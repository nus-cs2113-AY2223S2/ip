package duke.tasks;

public class Task {

    private String taskInfo;
    private boolean isDone;
    private String taskType = " ";

    /**
     * Initialises a Task object.
     * Sets the initial value of the task as undone.
     *
     * @param taskInfo Description of task
     * @param taskType Specifies the type of task
     */
    public Task(String taskInfo, String taskType) {
        setTaskInfo(taskInfo);
        this.isDone = false;
        setTaskType(taskType);
    }

    /**
     * Checks is a task is marked as done. If it is, it informs the user that the task was already marked as done.
     * If the task was not marked as done, it marks the task as done and informs the user.
     */
    public void markTaskAsDone() {
        if (isDone) {
            System.out.println("Task was already marked as done!");
        } else {
            isDone = true;
            System.out.println("Great! I've marked it as done!");
            System.out.println(this.getStatus() + this.taskInfo);
        }
    }

    public void copyCompletedTask() {
        isDone = true;
    }

    /**
     * Checks is a task is not marked as done. If it is marked as done, it sets the task to be undone and informs
     * the user.
     * If it is not marked as done, it informs the user that the task was not marked as done before.
     */
    public void markTaskAsUndone() {
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
}
