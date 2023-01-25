public class Task {
    protected String description;
    protected boolean isDone;

    protected void list(){

    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as DONE:");
        System.out.println(taskStatus());
    }

    public void markAsUndone(){
        this.isDone = false;
        System.out.println("OK! I've marked this task as NOT DONE YET:");
        System.out.println(taskStatus());
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String taskStatus(){
        return ("[" + getStatusIcon() + "] " + description);
    }
    //...
}
