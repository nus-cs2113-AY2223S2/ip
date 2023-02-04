package model.database;

import model.task.Task;

public class Database {

    protected final int MAX_TASK = 100;

    protected final Task[] tasks = new Task[MAX_TASK];

    protected static int counter = 0;

    /**
     * Used to keep track of the task count in the database
     * 
     * @return The amount of space used in the database
     */
    public static int getTaskCount() {
        return counter;
    }

    /**
     * A simple function to mimic the database CREATE functionality.
     * 
     * @param task The task to be set. The task will be set in the next
     * available index if there is still space.
     * 
     * @throws Exception An exception if the database is already full.
     */
    void create(Task task) throws Exception {
        if (counter >= MAX_TASK) {
            throw new Exception("The database is full. Don't ask me how");
        }
        tasks[counter] = task;
        counter += 1;
    }

    /**
     * A simple function to mimic the database READ functionality.
     * 
     * @param index The index of the task to be read.
     * @return The task at the index
     * @throws Exception An exception if an invalid index is provided.
     */
    Task read(int index) throws Exception {
        if (index >= counter || index < 0) {
            throw new Exception("Invalid index provided.");
        }
        return tasks[index];
    }
}
