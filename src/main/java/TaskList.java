import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing the task list of users
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructor for TaskList when there are no prior Tasks
     * from a previous session
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList when Tasks are present
     * from a previous session
     *
     * @param loadedTasks Array of Tasks to be added.
     */
    public TaskList(Task[] loadedTasks) {
        this();
        tasks.addAll(Arrays.asList(loadedTasks));
    }

    /**
     * Adds a Task to the task list
     *
     * @param task Task to be added to current list.
     */

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a task at a given index.
     *
     * @param i Index where task is to be returned.
     * @return The task requested by Duke.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes task from list at given index.
     *
     * @param i Index where task is to be removed.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Returns the number of items in the existing TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns an array of tasks stored in current Task List.
     *
     * @return An array of Tasks from current Task List.
     */
    public Task[] asList() {
        return tasks.toArray(new Task[0]);
    }

    /**
     * Filter the task list to only return tasks with the
     * specified keyword
     *
     * @param keyword the keyword which the filter is based on
     * @return a list of tasks that all contains the keyword specified
     */

    public ArrayList<Task> filterByKeyword(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i += 1) {
            String taskName = tasks.get(i).getDescription();
            if (taskName.contains(keyword)) {
                filteredTasks.add(tasks.get(i));
            }
        }
        return filteredTasks;
    }
}
