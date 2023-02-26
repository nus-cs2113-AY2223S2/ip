package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * A <code>TaskList</code> object contains the ArrayList of tasks from
 * user inputs. Handles the deletion of items and searching of list.
 */
public class TaskList {
    public static ArrayList<Task> lists;
    private Ui ui;

    public TaskList (ArrayList<Task> lists) {
        this.lists = lists;
    }

    /**
     * Lists all task recorded in the given list.
     *
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int itemNumber = 1;
        for (Task item : lists) {
            if ((itemNumber - 1) == lists.size()) {
                break;
            }
            item.printTask(itemNumber);
            itemNumber++;
        }
    }

    /**
     * Marks/unmarks or calls for deletion of task at the specified position.
     *
     * @param words Divided user input - to get item number of task.
     * @param line User input.
     * @throws IndexOutOfBoundsException If item number provided does not exist in ArrayList.
     */
    public void markOrDeleteTask(String[] words, String line) {
        try {
            int itemNumber = Integer.parseInt(words[1]);
            if (line.startsWith("mark")) {
                lists.get(itemNumber - 1).markAsDone();
            } else if (line.startsWith("unmark")) {
                lists.get(itemNumber - 1).markAsUndone();
            } else {
                DeleteTask(itemNumber);
            }
            Storage.saveDataFromInput(lists);
        } catch (IndexOutOfBoundsException e) {
            ui.printError();
        }
    }

    /**
     * Deletes task at the specified position.
     *
     * @param itemNumber Item number of task for deletion.
     */
    public void DeleteTask(int itemNumber) {
        int index = itemNumber - 1;
        Task item = lists.get(index);
        ui.printRemoveTask(item);
        lists.remove(index);
        ui.printListSize(lists.size());
    }
}
