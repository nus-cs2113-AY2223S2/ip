package tasks;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected static int numberOfTasks;

    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
        numberOfTasks++;
    }

    public void MarkTask(){
        printLines();
        if(this.isDone){
            System.out.println("tasks.Task.tasks.Task is already marked.");
        } else{
            this.isDone = true;
            System.out.println("Well Done. This rolex.task is marked as done:");
            System.out.println("[" + this.taskStatus() + "] " + this.taskName);
        }
        printLines();
    }

    public void unMarkTask(){
        printLines();
        if(this.isDone){
            this.isDone = false;
            System.out.println("Oh no, I've unmarked this rolex.task as it is not done:");
            System.out.println("[" + this.taskStatus() + "] " + this.taskName);
        } else{
            System.out.println("tasks.Task.tasks.Task is already unmarked.");
        }
        printLines();
    }

    public String taskStatus(){
        return (isDone ? "X" : " ");
    }

    public String toString(){
        return "[" + this.taskStatus() + "] " + this.taskName;
    }

} // tasks.Task.tasks.Task class ends here