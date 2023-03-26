package Duke.task;

import java.util.ArrayList;

import Duke.DukeException;
import Duke.DukeUi;

/**
 * DukeTaskList is the class that deals with the list of tasks.
 */
public class DukeTaskList {

    public ArrayList<DukeTask> tasksList;

    /**
     * Creates a new DukeTaskList object.
     */
    public DukeTaskList() {
        tasksList = new ArrayList<>();
    }

    /**
     * Creates a new DukeTaskList object.
     * 
     * @param tasksList the list of tasks
     */
    public DukeTaskList(ArrayList<DukeTask> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Deletes a task from the list of tasks.
     * 
     * @param task the task to be deleted
     */
    public void deleteTask(int index) throws DukeException {
        try {
            tasksList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task number is invalid.");
        }
    }

    /**
     * Matches the list of tasks to a keyword.
     * 
     * @param keyword the keyword to be searched
     * @param ui the user interface
     */
    public void findTasksList(String keyword, DukeUi ui) {
        ArrayList<DukeTask> foundTasksList = new ArrayList<>();
        for (DukeTask task : tasksList) {
            if (task.getName().contains(keyword)) {
                foundTasksList.add(task);
            }
        }
        ui.showList(foundTasksList, this);
    }

}
