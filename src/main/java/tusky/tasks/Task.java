package tusky.tasks;

import tusky.tasks.TaskType;
import tusky.tasks.EmptyDescriptionException;
public class Task {

    protected TaskType taskType = TaskType.TASK;
    protected boolean isDone;
    protected String description;

    public Task(String isDone, String description) throws EmptyDescriptionException{
        if(description.isBlank()){
            throw new EmptyDescriptionException("description of task is empty");
        }
        this.isDone = Boolean.parseBoolean(isDone);
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskSymbol() {
        // U for Unknown/Undefined
        return "U";
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public tusky.tasks.TaskType getTaskType(){
        return taskType;
    }

    public String getDetailedString(){
        return String.format("[%s][%s] %s", getTaskSymbol(), getStatusIcon(), this);
    }


    @Override
    public String toString(){
        return description;
    }

}
