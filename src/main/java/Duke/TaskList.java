package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {
    static int numberOfTasks = 0;
    static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds Todo to taskList and prints add task message.
     * @param command Array of string containing command arguments.
     * @throws DukeException Thrown when illegal arguments are give.
     */
    public static void addTodo(String[] command) {
        taskList.add(new Todo(command[1]));
        taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    /**
     * Adds Deadline to taskList and prints add task message.
     * @param command Array of string containing command arguments.
     * @throws DukeException Thrown when illegal arguments are give.
     */
    public static void addDeadline(String[] command) throws DukeException {
        if (!command[1].contains("/by")) {
            System.out.println("Error: Use /by");
            throw new DukeException();
        }
        String[] deadLineInputs = command[1].split("/by ", 2);
        taskList.add(new Deadline(deadLineInputs[0], deadLineInputs[1]));
        taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    /**
     * Adds event to taskList and prints add task message.
     * @param command Array of string containing command arguments.
     * @throws DukeException Thrown when illegal arguments are give.
     */
    public static void addEvent(String[] command) throws DukeException {
        if (!(command[1].contains("/from ") && command[1].contains("/to "))) {
            System.out.println("Error: Use /from and /to");
            throw new DukeException();
        }

        String[] eventInputs = command[1].split("/from|/to");
        taskList.add(new Event(eventInputs[0], eventInputs[1], eventInputs[2]));
        taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    /**
     * Marks or unmarks specified task in taskList based on command and
     * prints mark or unmark message respectively.
     * @param command Array of string containing command arguments.
     * @throws DukeException Thrown when illegal index is given.
     */
    public static void doMarkOrUnmarked(String[] command) throws DukeException {
        int indexToChange = Integer.parseInt(command[1]) - 1;
        //Check for error
        if (indexToChange >= numberOfTasks || indexToChange < 0) {
            System.out.println("Index not found");
            throw new DukeException();
        }
        if (command[0].equals("mark")) {
            taskList.get(indexToChange).setDone();
            taskList.get(indexToChange).printMarkedTask();
        } else {
            taskList.get(indexToChange).setNotDone();
            taskList.get(indexToChange).printUnmarkedTask();
        }
    }

    /**
     * Removes task of specified index from taskList and prints delete message.
     * @param indexToDelete Index of task to delete
     */
    public static void deleteTask(int indexToDelete) {
        taskList.get(indexToDelete).printDeleteTask(numberOfTasks);
        taskList.remove(indexToDelete);
    }

    /***
     * Iterates taskList and prints each tasks with respective information
     * when "list" command is issued.
     */
    static void doList() {
        System.out.println(Ui.BLANK + Ui.LINE);
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print(Ui.BLANK + (i + 1) + ".");
            taskList.get(i).printTask();
        }
        System.out.println(Ui.BLANK + Ui.LINE);
    }

    /**
     * Initializes numberOfTasks and taskList when running duke.
     * @throws FileNotFoundException Thrown when save file is not found
     */
    static void initializeTaskNumberAndList() {
        try {
            numberOfTasks += Storage.initializeTaskList(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found");
        }
    }
}
