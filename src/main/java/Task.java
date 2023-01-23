import java.util.ArrayList;

public class Task {
    
    public static ArrayList<Task> tasks = new ArrayList<Task>(); 

    String description;
    boolean isComplete;

    public static String barrier = "____________________________________________________________";

    public Task (){
        this.description = "";
        this.isComplete = false;
        tasks.add(this);
    }

    public Task (String description) {
        this.description = description;
        this.isComplete = false;
        tasks.add(this);
    }

    public void mark (){
        if (this.isComplete){
            System.out.println("\nOops! This task is already complete!\n" + barrier + "\n");
            return;
        }
        this.isComplete = true;
        System.out.println("\nNice! I've marked this task as done: \n");
        System.out.println("     " + this.printTask() + "\n" + barrier + "\n");
    }

    public void unmark (){
        if (!this.isComplete){
            System.out.println("\nOops! This task is already incomplete!\n" + barrier + "\n");
            return;
        }
        this.isComplete = false;
        System.out.println("\nOk, I've marked this task as not done yet: \n");
        System.out.println("     " + this.printTask() + "\n"  + barrier + "\n");
    }

    public String printTask (){
        if (this.isComplete){
            return "[X] " + this.description;
        }
        return "[ ] " + this.description; 
    }

    public static ArrayList<Task> getTasksArray(){
        return tasks;
    }

    public static void printAllTasks(){
        System.out.println("Time for a productive day! Here's your list of tasks:");
        for(int i = 0; i<tasks.size(); i++){
            System.out.println((i+1) + ". " + tasks.get(i).printTask());
        }
    }
}