package duke.tasklist;

import duke.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents the list of tasks that are stored and saved.
 * There are three types of tasks - Todo, Deadline, and Event.
 */
public class TaskList {
    private static final String line = "__________________________________________________________";
    //private static Ui ui;
    protected static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;

    /**
     * Creates a new TaskList with the given tasks in the ArrayList.
     * @param tasks An ArrayList storing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks that are stored.
     * @return the list of tasks.
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the specific information of a task in the list.
     * @param x The index of the task in the list.
     * @return The task specified by its index.
     */
    public static Task getTask(Integer x) {
        return tasks.get(x-1);
    }

    /**
     * Get the number of tasks that has been stored in the list.
     * @return the number of tasks in the list.
     */
    public static int getNumTasks() {
        return numTasks;
    }

    /**
     * Checks if a new task to be added into the list is of a correct format.
     * @param userInput Command given by user to execute.
     *                  Should include the type of task and task description.
     * @throws IndexOutOfBoundsException
     */
    public static void validTask(String[] userInput) throws IndexOutOfBoundsException{
        if (userInput.length < 2 && (userInput[0].equals("todo") ||
                userInput[0].equals("event") || userInput[0].equals("deadline"))) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Mark a task as done or unmark a task upon checking if the user input is valid.
     * @param userInput Command given by user to execute.
     *                  Should include action and task index.
     * @throws IndexOutOfBoundsException
     */
    public static void markValidTask(String[] userInput) throws IndexOutOfBoundsException {
        int x = Integer.parseInt(userInput[1]);
        if (tasks.get(x-1) == null || tasks.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(x-1).markAsDone(userInput[0]);
    }

    /**
     * Adds a new task into the list of tasks.
     * @param userInput Command given by user to execute.
     *                  Should include the type of task and
     *                  task description in the correct format.
     * @throws DukeException
     */
    public static void addTask(String userInput) throws DukeException {
        Task t;
        String[] words = userInput.split(" ");
        validTask(words);
        String descriptor = userInput.substring(userInput.indexOf(words[1]), userInput.length());
        if (words[0].equals("todo")) {
            t = new Todo(descriptor);
            Ui.printMessage(t, Ui.CommandType.TODO);
        } else if (words[0].equals("deadline")) {
            String by = descriptor.split("/by ")[1];
            descriptor = descriptor.split("/by ")[0];
            t = new Deadline(descriptor, by);
            Ui.printMessage(t, Ui.CommandType.DEADLINE);
        } else if (words[0].equals("event")) {
            String to = descriptor.split("/to ")[1];
            String from = descriptor.split(" /")[1];
            descriptor = descriptor.split("/")[0];
            t = new Event(descriptor, from, to);
            Ui.printMessage(t, Ui.CommandType.EVENT);
        } else {
            throw new IndexOutOfBoundsException();
        }
        tasks.add(t);
        numTasks = tasks.size();
    }

    /**
     * Delete a task from the list using its index.
     * @param userInput Command by user to execute.
     *                  Should include the index of the task.
     * @throws DukeException
     */
    public static void deleteTask(String userInput) throws DukeException {
        String taskNum = userInput.substring(userInput.length()-1);
        int x = Integer.parseInt(taskNum);
        if (tasks.get(x-1) == null || tasks.size() == 0) {
            throw new DukeException();
        }
        Task temp = tasks.get(x-1);
        tasks.remove(x-1);
        numTasks = tasks.size();
        Ui.printMessage(temp, Ui.CommandType.DELETE);
    }
}
