public class Task {
    protected String taskName;
    protected boolean isDone;
    protected static int numberOfTasks;

    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
        numberOfTasks++;
    }

    public void MarkTask(){
        Rolex.printLines();
        if(this.isDone){
            System.out.println("Task is already marked.");
        } else{
            this.isDone = true;
            System.out.println("Well Done. This task is marked as done:");
            System.out.println("[" + this.taskStatus() + "] " + this.taskName);
        }
        Rolex.printLines();
    }

    public void unMarkTask(){
        Rolex.printLines();
        if(this.isDone){
            this.isDone = false;
            System.out.println("Oh no, I've unmarked this task as it is not done:");
            System.out.println("[" + this.taskStatus() + "] " + this.taskName);
        } else{
            System.out.println("Task is already unmarked.");
        }
        Rolex.printLines();
    }

    public String taskStatus(){
        return (isDone ? "X" : " ");
    }

    public String toString(){
        return "[" + this.taskStatus() + "] " + this.taskName;
    }

} // Task class ends here