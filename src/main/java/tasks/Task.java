package tasks;

import java.util.List;

public class Task {

    protected String taskSign;
    protected String taskName;
    protected boolean isDone;
    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public Task(String taskName, boolean test, String taskSign){
        this.taskName = taskName;
        this.taskSign = taskSign;
        this.isDone = test;
    }

    public void MarkTask(){
        printLines();
        if(this.isDone){
            System.out.println("Task is already marked.");
        } else{
            this.isDone = true;
            System.out.println("Well Done. This task is marked as done:");
            System.out.println(this.taskSign + "[" +this.taskStatus()+ "] " + this.taskName);
        }
        printLines();
    }

    public void unMarkTask(){
        printLines();
        if(this.isDone){
            this.isDone = false;
            System.out.println("Oh no, I've unmarked this task as it is not done:");
            System.out.println(this.taskSign + "[" +this.taskStatus()+ "] " + this.taskName);
        } else{
            System.out.println("Task is already unmarked.");
        }
        printLines();
    }

    public static void deleteTask(List<Task> task, int indexToDelete){
        printLines();
        if(indexToDelete >= 1 && indexToDelete <= task.size()){
            System.out.println("Noted. I've removed this task.");
            System.out.println(task.get(indexToDelete-1).taskSign + "["
                    + task.get(indexToDelete-1).taskStatus() + "] "
                    + task.get(indexToDelete-1).taskName);

            task.remove(indexToDelete-1);
            System.out.println("\nNow you have " + task.size() + " tasks in the list.");
        } else{
            System.out.println("Invalid index. Please enter valid index number!");
        }
        printLines();
    }

    public String taskStatus(){
        return (isDone ? "X" : " ");
    }

    public String toString(){
        return "[" + this.taskStatus() + "] " + this.taskName;
    }

} // Task class ends here