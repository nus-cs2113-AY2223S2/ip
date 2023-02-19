package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    public static ArrayList<Task> textList = new ArrayList<>();

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public static void addTask(Task task) {
        textList.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param index The index of the task to be deleted.
     */
    public static void deleteTask(int index) {
        textList.remove(index);
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     */
    public static void markTask(int index) {
        textList.get(index).markAsDone();
    }

    /**
     * Marks a task as not done.
     * @param index The index of the task to be marked as not done.
     */
    public static void unmarkTask(int index) {
        textList.get(index).unmarkAsDone();
    }

    /**
     * Returns the size of the list.
     * @return The size of the list.
     */
    public static int getSize() {
        return textList.size();
    }

    /**
     * Returns the task at the given index.
     * @param index The index of the task to be returned.
     * @return The task at the given index.
     */
    public static Task getTask(int index) {
        return textList.get(index);
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks.
     */
    public static ArrayList<Task> getList() {
        return textList;
    }

    /**
     * Returns the list of tasks that contain the given keyword.
     * @param keyword The keyword to be searched for.
     * @return The list of tasks that contain the given keyword.
     */
    public static ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < textList.size(); i++) {
            if (textList.get(i).description.contains(keyword)) {
                foundTasks.add(textList.get(i));
            }
        }
        return foundTasks;
    }
}
