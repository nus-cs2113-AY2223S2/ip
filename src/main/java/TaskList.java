import java.sql.Array;
import java.util.ArrayList;
public class TaskList {

    /**
     * Constructor for TaskList Class
     * @param userTasks ArrayList<Task> which contains user tasks
     */
    public TaskList(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }
    private ArrayList<Task> userTasks;

    /**
     * Getter function for ArrayList<Task> userTasks
     * @return ArrayList<Task> userTasks
     */
    public ArrayList<Task> getUserTasksArrayList() {
        return userTasks;
    }

    /**
     * Getter function for task in userTasks using index.
     * @param index index of task to return
     * @return Task at the given index
     */
    public Task getUserTask(int index) {
        return userTasks.get(index);
    }

    /**
     * Adds new task into userTasks ArrayList
     * @param newTask Task to be added.
     */
    public void addUserTask(Task newTask) {
        userTasks.add(newTask);
    }

    /**
     * Removes task at given index from userTasks ArrayList
     * @param index index of task to be removed
     */
    public void removeUserTask(int index) {
        userTasks.remove(index);
    }

    /**
     * Returns number of user tasks currently present in ArrayList<Task> userTasks
     * @return number of user tasks
     */
    public int getNumberOfUserTasks() {
        return userTasks.size();
    }

    /**
     * Setter function to set task as done at given index
     * @param index index of task to be set as done
     */
    public void setTaskDone(int index) {
        userTasks.get(index).setisDone(true);
    }

    /**
     * Setter function to set task as not done at given index
     * @param index index of task to be set as not done
     */
    public void setTaskNotDone(int index) {
        userTasks.get(index).setisDone(false);
    }

    public ArrayList<Integer> findTasksBasedOnName(String name) {
        ArrayList<Integer> tasksIndexWithSimilarName = new ArrayList<>();
        for(int i = 0; i < userTasks.size(); i++) {
            String taskName = userTasks.get(i).getTaskName();
            taskName = taskName.toLowerCase();
            int indexOfSearchedName = taskName.indexOf(name.toLowerCase());
            if (indexOfSearchedName != -1) {
                tasksIndexWithSimilarName.add(i);
            }
        }
        return tasksIndexWithSimilarName;
    }
}
