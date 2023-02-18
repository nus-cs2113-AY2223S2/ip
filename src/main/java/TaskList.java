import java.util.ArrayList;

/**
 * Represents a list of tracked tasks which can also perform operations on these
 * tracked tasks.
 */
public class TaskList {
    protected static final int TASK_TYPE_INDEX = 0;
    protected static final int IS_DONE_INDEX = 1;
    protected static final int DESCRIPTION_INDEX = 2;
    protected static final int BY_INDEX = 3;
    protected static final int FROM_INDEX = 3;
    protected static final int TO_INDEX = 4;

    protected ArrayList<Task> tasks;
    protected int numOfTasks = 0;

    /**
     * Initialise tasks variable to an empty ArrayList<Task>.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialise tasks variable to an array containing previously saved tasks.
     * @param savedTasks Array of previously saved tasks, each tasks represented as strings
     */
    public TaskList(ArrayList<String> savedTasks) {
        this.tasks = new ArrayList<>();
        for (String line : savedTasks) {
            addOldTask(line);
        }
    }

    public void incrementNumOfTasks() {
        numOfTasks++;
    }

    public void decrementNumOfTasks() {
        numOfTasks--;
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public void addTask(Task task) {
        tasks.add(numOfTasks, task);
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).setTaskStatus(true);
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).setTaskStatus(false);
    }

    /**
     * Determine which type of previously saved task needs to be added.
     * @param line A string representation of a saved task that needs to be added
     */
    public void addOldTask(String line) {
        // Retrieve task type from text file
        String[] taskData = line.split("\\|");

        // Determine what kind of task to add
        switch (taskData[TASK_TYPE_INDEX]) {
        case "T":
            addOldTodo(taskData);
            break;
        case "D":
            addOldDeadline(taskData);
            break;
        case "E":
            addOldEvent(taskData);
            break;
        }
    }

    /**
     * Take the previously saved Todo task and add it to the ArrayList<Task> tasks.
     * @param taskData A string array of different parts of info regarding the Todo task
     */
    public void addOldTodo(String[] taskData) {
        addTask(new Todo(taskData[DESCRIPTION_INDEX]));

        int isDone = Integer.parseInt(taskData[IS_DONE_INDEX]);
        if (isDone == 1) {
            markTask(numOfTasks);
        } else {
            unmarkTask(numOfTasks);
        }
        incrementNumOfTasks();
    }

    /**
     * Take the previously saved Deadline task and add it to the ArrayList<Task> tasks.
     * @param taskData A string array of different parts of info regarding the Deadline task
     */
    public void addOldDeadline(String[] taskData) {
        String description = taskData[DESCRIPTION_INDEX];
        String by = taskData[BY_INDEX];
        addTask(new Deadline(description, by));

        int isDone = Integer.parseInt(taskData[IS_DONE_INDEX]);
        if (isDone == 1) {
            markTask(numOfTasks);
        } else {
            unmarkTask(numOfTasks);
        }
        incrementNumOfTasks();
    }

    /**
     * Take the previously saved Event task and add it to the ArrayList<Task> tasks.
     * @param taskData A string array of different parts of info regarding the Event task
     */
    public void addOldEvent(String[] taskData) {
        String description = taskData[DESCRIPTION_INDEX];
        String from = taskData[FROM_INDEX];
        String to = taskData[TO_INDEX];
        addTask(new Event(description, from, to));

        int isDone = Integer.parseInt(taskData[IS_DONE_INDEX]);
        if (isDone == 1) {
            markTask(numOfTasks);
        } else {
            unmarkTask(numOfTasks);
        }
        incrementNumOfTasks();
    }
}
