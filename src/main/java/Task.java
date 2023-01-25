import java.util.ArrayList;

public class Task {
    
    // Static ArrayList of all tasks
    public static ArrayList<Task> tasks = new ArrayList<Task>(); 

    // Task class attributes
    String description;
    boolean isComplete;

    // Reusable barrier
    public static String barrier = "____________________________________________________________";

    // Sets default task to no description and incomplete then adds to list
    public Task (){
        this.description = "";
        this.isComplete = false;
        tasks.add(this);
    }

    // Dynamic task constructor
    public Task (String description) {
        this.description = description;
        this.isComplete = false;
        tasks.add(this);
    }

    // Changes task isComplete attribute to true
    public void markAsComplete (){
        if (this.isComplete){
            System.out.println("\nBlast! This task be already complete, ye swab!\n" + barrier + "\n");
            return;
        }
        this.isComplete = true;
        System.out.println("\nNice! I've marked this task as done, me hearties!\n");
        System.out.println("     " + this.printTask() + "\n" + barrier + "\n");
    }

    // Changes task isComplete attribute to true
    public void unmarkAsComplete (){
        if (!this.isComplete){
            System.out.println("\nBlast! This task be already incomplete, ye bilge rat!\n" + barrier + "\n");
            return;
        }
        this.isComplete = false;
        System.out.println("\nAye, I've marked this task as not done yet, ye scallywag: \n");
        System.out.println("     " + this.printTask() + "\n"  + barrier + "\n");
    }

    // Prints the task completion status and description
    public String printTask (){
        if (this.isComplete){
            return "[X] " + this.description;
        }
        return "[ ] " + this.description; 
    }

    // Accessor method for the list of tasks
    public static ArrayList<Task> getTasksArray(){
        return tasks;
    }

    // Prints all tasks
    public static void printAllTasks(){
        System.out.println("Time for a productive day, me hearties! Here be yer list of tasks: ");
        for(int i = 0; i<tasks.size(); i++){
            System.out.println((i+1) + ". " + tasks.get(i).printTask());
        }
    }
}