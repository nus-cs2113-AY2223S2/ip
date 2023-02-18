package data;

import java.util.ArrayList;
import duke.Task;

/**
 * Represents a list where all the user created tasks are being stored in
 * Duke program. A <code>tasksList</code> object corresponds to an ArrayList
 * that contains all tasks created by the user.
 */
public class tasksList {
    public static ArrayList<Task> userTasksList = new ArrayList<>();
    public static int userTaskCount = 0;

    public tasksList() {
    };

    public static ArrayList<Task> getTaskArrayList() {
        return userTasksList;
    }

    public static void addTask(Task taskToAdd) {
        userTasksList.add(taskToAdd);
    }

    public static void deleteTask(int index) {
        userTasksList.remove(index);
    }

    public static void markTaskDone(int index) {
        userTasksList.get(index).markAsDone();
    }

    public static void markTaskUndone(int index) {
        userTasksList.get(index).markAsUndone();
    }
}
