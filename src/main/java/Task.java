import java.util.ArrayList;

public class Task {
    
    public static ArrayList<Task> tasks = new ArrayList<Task>(); 

    String description;
    boolean isComplete;

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

    public void completeTask (){
        this.isComplete = true;
    }

    public String printTask (){
        return this.description;
    }

    public static void printAllTasks(){
        for(int i = 0; i<tasks.size(); i++){
            System.out.println((i+1) + ". " + tasks.get(i).printTask());
        }
    }
}