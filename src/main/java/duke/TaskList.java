/**
 * Contains a TaskList that is an ArrayList of tasks
 * addTask adds a task to TaskList
 * getTaskArray returns entire TaskList
 * getIndex returns the size of TaskList
 *
 * @param taskArray An ArrayList that contains Tasks
 */

package duke;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArray;

    public TaskList() {
        taskArray = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskArray.add(task);
    }

    public ArrayList<Task> getTaskArray() {
        return taskArray;
    }

    public int getIndex() {
        return taskArray.size();
    }


}
