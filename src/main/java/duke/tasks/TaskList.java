package duke.tasks;

import duke.storage.Database;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Task list class that contains the current list of task objects.
 */
public class TaskList {

    private static Database database;
    public ArrayList<Task> tasks;

    /**
     * Constructor of the TaskList where it takes in the stored tasks in the database's Arraylist of tasks.
     */
    public TaskList(){
        database = new Database();
        tasks = database.taskList;
    }

    /**
     * Returns the current number of tasks in the task list.
     *
     * @return The current number of tasks in the task list.
     */
   public int listCount() {
       return tasks.size();
   }

    /**
     * Appends the given task inputted by the user to the task list and the database.
     *
     * @param task The task that would be added into task list and the database.
     */
    public void addTasks (Task task) {
        tasks.add(task);
        addToDatabase(task);
    }

    /**
     * Retrieves the task at the given index of the task list.
     *
     * @param index The given index that corresponds to the position of a task in a list.
     * @return The task at the given index of the task list.
     */
    public Task getTask(int index) {
       return tasks.get(index);
    }

    /**
     * Deletes the task at the given index of the task list.
     *
     * @param index The given index that corresponds to the position of a task in a list.
     */
    public void deleteTasks(int index) {
        tasks.remove(index);
        try {
            database.updateDatabase();
        } catch (IOException e){
            System.out.println("Failed to update database");
        }
    }

    /**
     * Appends the task to the database.
     *
     * @param currTask The task that is to be added and saved in the database.
     */
    public static void addToDatabase(Task currTask){
        try {
            database.appendSaveTasks(currTask.taskInformation());
        } catch (IOException e){
            System.out.println("Failed to append task to database");
        }
    }
}
