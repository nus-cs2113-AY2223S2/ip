package Duke;

public class Task {

    protected String taskName;
    protected boolean isCompleted;

    protected String taskType;





    public Task(String description) {
        this.taskName = description;
        this.isCompleted = false;
        this.taskType = "T";


    }


    public void setTaskName(String name) {
        this.taskName = name;

    }

    public void setAsTask() {
        this.taskType = "T";
    }

    public String getTaskType(Task task) {
        return this.taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskProgress(boolean inputCommand) {
        this.isCompleted = inputCommand;
    }

    public boolean getTaskProgress() {
        return this.isCompleted;
    }


    public void printTask() {
        if (this.isCompleted) {
            System.out.println(".[" + getTaskType(this) + "]"+ this.getStatusIcon()  + this.taskName);
        } else {
            System.out.println(".[" + getTaskType(this) + "]"+ this.getStatusIcon() +  this.taskName);
        }

    }


    public String getStatusIcon() {
        return (isCompleted ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isCompleted = true;
    }

    public void markAsNotDone() {
        this.isCompleted = false;
    }


}
