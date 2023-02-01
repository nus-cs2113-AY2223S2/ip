public class Tasks {
    protected String TaskName;
    protected boolean isDone;
    protected static int NumberOfTasks;

    public Tasks(String TaskName){
        this.TaskName = TaskName;
        this.isDone = false;
        NumberOfTasks++;
    }

    public void MarkTask(){
        this.isDone = true;

        Rolex.printLines();
        System.out.println("Well Done. This task is marked as done:");
        System.out.println("[" + this.TaskStatus() + "] " + this.TaskName);
        Rolex.printLines();
    }

    public void unMarkTask(){
        this.isDone = false;

        Rolex.printLines();
        System.out.println("Oh no, I've unmarked this task as it is not done:");
        System.out.println("[" + this.TaskStatus() + "] " + this.TaskName);
        Rolex.printLines();
    }

    public String TaskStatus(){
        return (isDone ? "X" : " ");
    }

    public String toString(){
        return "[" + this.TaskStatus() + "] " + this.TaskName;
    }

}