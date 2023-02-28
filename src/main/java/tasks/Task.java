package tasks;

/**
 * The abstract task object. Contains the subclasses ToDo, Deadline and Event. Has the members
 * name, completionStatus and taskType that is shared among all subclasses.
 */

public abstract class Task {
    private String name;
    private boolean completionStatus;
    private String taskType;

    /**
     * Creates a task. Should almost never be used outside of the tasks package. The specific task
     * type constructors should be used instead.
     * 
     * @param name the name of the task
     * @param taskType the type of the task
     */

    public Task(String name, String taskType) {
        this.name = name;
        this.taskType = taskType;
        this.completionStatus = false;
    }

    public String getName() {
        return this.name;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setStatus(boolean status) {
        this.completionStatus = status;
    }

    public boolean isDone() {
        return this.completionStatus;
    }

    public String toString() {
        String completionStatusString = completionStatus ? "[X]" : "[ ]";
        String taskTypeString = '[' + taskType + ']';
        return taskTypeString + completionStatusString + ' ' + name;
    }

    /**
     * Returns the string representation of the task in the savefile format
     * 
     * @return string representation of the task in savefile format
     */

    public String toSaveString() {
        String completionStatusString = completionStatus ? "Y" : "N";
        return taskType + '|' + completionStatusString + '|' + name;
    }
}
