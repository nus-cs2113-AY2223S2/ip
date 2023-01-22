public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public static void markAsDone(Task task) {

        task.isDone = true;


    }

    public static void markAsNotDone(Task task) {

        task.isDone = false;


    }

    public String getDescription(){
        return(this.description);
    }
}
