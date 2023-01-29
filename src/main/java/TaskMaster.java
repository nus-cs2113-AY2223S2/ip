import java.util.Arrays;

/**
 * The TaskMaster class is responsible for managing and storing the tasks that are added by the user.
 * <p>
 * It uses an array of TaskManager objects to store the tasks and provides methods for adding new tasks,
 * <p>
 * getting a specific task, and printing the list of tasks.
 */
public class TaskMaster {
    //initialise an array to store the tasks
    private TaskManager[] toDo;

    /**
     * Constructor for TaskMaster class. It initializes the array for storing tasks with a default size of 0.
     */
    public TaskMaster() {
        this.toDo = new TaskManager[0];
    }

    /**
     * Method for adding a new task to the list of tasks. It takes a string as an input representing the task to be added.
     * The size of the array is increased by 1 and the new task is added to the end of the array.
     *
     * @param toAdd the task to be added to the list.
     */
    public void addNewItem(String toAdd) {
        //instead of a fixed sized array, use an array that 'dynamically' increases in size for every task added
        int listLength = toDo.length;
        this.toDo = Arrays.copyOf(this.toDo, listLength + 1);
        this.toDo[listLength] = new TaskManager(toAdd);
    }

    /**
     * Method for getting a specific task from the list of tasks. It takes an integer as an input representing the index of the task.
     *
     * @param index the index of the task to be retrieved.
     * @return the TaskManager object representing the task at the given index.
     */
    public TaskManager getTask(int index) {
        return this.toDo[index];
    }

    /**
     * Method for printing the list of tasks. It prints a message if the list is empty.
     * If not, it iterates through the array of tasks, printing each one along with its status.
     */
    public void printList() {
        //this is only for UX purposes
        if (this.toDo.length == 0) {
            System.out.println("Hm... It looks like you have not added any tasks.\n");
        }
        for (int i = 0; i < this.toDo.length; i++) {
            this.toDo[i].printTaskAndStatus(i);
        }
    }
}
