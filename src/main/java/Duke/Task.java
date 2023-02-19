package Duke;

public class Task {

    protected String taskName;
    protected boolean isCompleted;

    protected String taskType;


    /**
     * Creates a template for each task, which includes the name, its completion status and task type
     * @param description name of task
     */

    public Task(String description) {
        setTaskName(description);
        this.isCompleted = false;
        this.taskType = "T";


    }

    /**
     * Sets the name of the task
     * @param name Name of the task
     */

    public void setTaskName(String name) {
        this.taskName = name;

    }


    /**
     * Returns the type of task
     * @param task task to find the type for
     * @return type of the task
     */
    public String getTaskType(Task task) {
        return this.taskType;
    }

    /**
     * Returns the name of task
     * @return name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Marks task as completed or not
     * @param isCompleted boolean to set if the task is completed
     */
    public void setTaskProgress(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * prints all information about the task
     */

    public void printTask() {
        if (this.isCompleted) {
            System.out.println(".[" + getTaskType(this) + "]"+ this.getStatusIcon()  + this.taskName);
        } else {
            System.out.println(".[" + getTaskType(this) + "]"+ this.getStatusIcon() +  this.taskName);
        }

    }


    /**
     * Returns string to show if task is completed to the user interface
     * @return String showing if a task is completed
     */
    public String getStatusIcon() {
        return (isCompleted ? "[X]" : "[ ]");
    }

    /**
     * sets a task as completed
     */

    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * sets a task as not completed
     */
    public void markAsNotDone() {
        this.isCompleted = false;
    }


}
