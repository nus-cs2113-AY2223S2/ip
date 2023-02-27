package util;

import java.util.ArrayList;

/**
 * provides methods for creating a list of tasks and initializing it, and for
 * finding tasks containing a keyword.
 */
public class TaskList {
    public static ArrayList<Task> createList() {
        ArrayList<Task> commands = new ArrayList<Task>();
        return commands;
    }

    /**
     * Searches for all tasks in the given list of commands that contain the
     * specified keyword
     * in their description, and returns a list of their indexes in the original
     * list.
     *
     * @param keyword  The keyword to search for in the task descriptions.
     * @param commands The list of tasks to search through.
     * @return An ArrayList of integers representing the indexes of the tasks that
     *         contain the keyword.
     */
    public static ArrayList<Integer> find(String keyword, ArrayList<Task> commands) {
        ArrayList<Integer> relatedIndexes = new ArrayList<Integer>();
        for (int i = 0; i < commands.size(); i++) {
            Task task = commands.get(i);
            if (task.description.contains(keyword)) {
                relatedIndexes.add(i);
            }
        }
        return relatedIndexes;
    }

    public TaskList() {
    }

}
