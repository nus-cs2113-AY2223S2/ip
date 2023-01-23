public class Task {
    private String taskDesc;
    private boolean isDone = false;

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getTaskPrint() {  
        return getStatusIcon() + " " + getTaskDesc();
    }

    public void setDone(boolean done) {
        isDone = done;
        GrandDuke.println();
        if (isDone) {
            GrandDuke.output("Alright!, I helped you mark this task as done:");
        } else {
            GrandDuke.output("OK, I helped you mark this task as not done yet:");
        }
        GrandDuke.output("  " + getTaskPrint());
        GrandDuke.println();
    }

}