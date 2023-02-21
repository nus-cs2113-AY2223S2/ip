package duke.tasks;

import duke.storage.Database;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private static Database database;
    public ArrayList<Task> tasks;
    public TaskList(){
        database = new Database();
        tasks = database.taskList;
    }

   public int listCount() {
       return tasks.size();
   }

    public void addTasks (Task task) {
        tasks.add(task);
        addToDatabase(task);
    }

    public Task getTask(int index) {
       return tasks.get(index);
    }

    public void deleteTasks(int index) {
        tasks.remove(index);
        try {
            database.updateDatabase();
        } catch (IOException e){
            System.out.println("Failed to update database");
        }
    }

    public static void addToDatabase(Task currTask){
        try {
            database.appendSaveTasks(currTask.taskInformation());
        } catch (IOException e){
            System.out.println("Failed to append task to database");
        }
    }
}
