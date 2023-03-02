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
}
