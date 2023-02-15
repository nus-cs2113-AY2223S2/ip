package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;


    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
        this.taskType = taskType;
    }
    public void markAsDone() {
        isDone = true;
    }

<<<<<<< HEAD
    public void markAsUndone() {
        isDone = false;
    }
    // mark done task with X on 2nd []
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
=======
    public void markAsDone() {

        isDone = true;
>>>>>>> branch-Level-7
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

<<<<<<< HEAD
    public String toString(){

        return  "[" + getStatusIcon() + "]"  + description;
    }
    public String saveText(){
=======
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String saveText() {
>>>>>>> branch-Level-7
        return "";
    }

}
