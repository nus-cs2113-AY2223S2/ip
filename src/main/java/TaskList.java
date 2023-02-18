import java.util.ArrayList;

public class TaskList {
    protected static final int TASK_TYPE_INDEX = 0;
    protected static final int IS_DONE_INDEX = 1;
    protected static final int DESCRIPTION_INDEX = 2;
    protected static final int BY_INDEX = 3;
    protected static final int FROM_INDEX = 3;
    protected static final int TO_INDEX = 4;

    protected ArrayList<Task> tasks;
    protected int numOfTasks = 0;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> savedTasks) {
        this.tasks = new ArrayList<>();
        for (String line : savedTasks) {
            addOldTasks(line);
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

    public void addOldTasks(String line) {
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

    // Add a saved Todo task to ArrayList
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

    // Add a saved Deadline task to ArrayList
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

    // Add a saved Event task to ArrayList
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

    // Add past saved tasks to ArrayList

}
