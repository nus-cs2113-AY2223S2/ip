package duke.tasks;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    // Accessor method for TaskList
    public static ArrayList<Task> getTasksArray() {
        return tasks;
    }

    // Prints all tasks
    public static void printAllTasks() {
        System.out.println("Time for a productive day, me hearties! Here be yer list of tasks: ");
        for(int i = 0; i<tasks.size(); i++){
            System.out.println((i+1) + ". " + tasks.get(i).printTask());
        }
    }
    
    public static void add(Task task) {
        tasks.add(task);
    }
}