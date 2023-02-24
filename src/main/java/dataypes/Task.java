package dataypes;

public class Task  implements TaskFileHandler {
    /** Name of the task **/
    protected String description;
    /** Boolean to indicate if task is done or not **/
    protected boolean isDone;

    public Task() {}


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public boolean getStatus() {
        return this.isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    public String getDescription() {
        return this.description;
    }
    public String getStatusAndDescription() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
//    public String printAddedTask() {
//
//    }
    public void markTask() {
        this.isDone = true;
    }
    public void unMarkTask() {
        this.isDone = false;
    }
    public String enCode() {
        return getStatus() + " # " + getDescription();
    }
    public void deCode(String userInput) {
        ;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
