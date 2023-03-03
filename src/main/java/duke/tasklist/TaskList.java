package duke.tasklist;

import duke.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks that are stored and saved.
 * There are three types of tasks - To-do, Deadline, and Event.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    protected static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * The number of tasks in the list.
     */
    private static int numTasks = 0;

    /**
     * Creates a new TaskList with the given tasks in the ArrayList.
     * @param tasks An ArrayList storing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
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
     * @throws IndexOutOfBoundsException if user input is out of range.
     */
    public static void validTask(String[] userInput) throws IndexOutOfBoundsException{
        boolean isLongInput = userInput.length >= 2;
        boolean isTodo = userInput[0].equals("todo");
        boolean isEvent = userInput[0].equals("event");
        boolean isDeadline = userInput[0].equals("deadline");
        if (!isLongInput && (isTodo ||
                isEvent || isDeadline)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Mark a task as done or unmark a task upon checking if the user input is valid.
     * @param userInput Command given by user to execute.
     *                  Should include action and task index.
     * @throws IndexOutOfBoundsException if task to be marked is out of bounds.
     */
    public static void markValidTask(String[] userInput) throws IndexOutOfBoundsException {
        boolean isLongInput = userInput.length >= 2;
        if (!isLongInput) {
            throw new NumberFormatException();
        }
        try {
            int taskNum = Integer.parseInt(userInput[1]);
            if (tasks.get(taskNum - 1) == null || tasks.size() == 0) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(taskNum - 1).setDone(userInput[0]);
        } catch (NumberFormatException e) {
            Ui.printMessage(Ui.CommandType.NUMBERFORMAT);
        }
    }

    /**
     * Reads tasks from the list stored in the txt file.
     * @param taskList The list of tasks stored in the txt file.
     * @throws IndexOutOfBoundsException if task cannot be found and read.
     */
    public static void readTask(List<String> taskList) throws IndexOutOfBoundsException {
        for(String task: taskList) {
            Task newTask;
            String type = task.substring(1,2);
            String status = task.substring(4,5); //"X" or " "
            switch (type) {
            case "T": {
                String descriptor = task.substring(7);
                newTask = new Todo(descriptor);
                break;
            }
            case "D": {
                String descriptor = task.substring(7, task.indexOf("(by: "));
                String by = task.substring(task.indexOf("(by: ") + 5, task.indexOf(")"));
                newTask = new Deadline(descriptor, by);
                break;
            }
            case "E": {
                String descriptor = task.substring(7, task.indexOf("(from: "));
                String from = task.substring(task.indexOf("(from: ") + 7, task.indexOf("to: "));
                String to = task.substring(task.indexOf("to: ") + 4, task.indexOf(")"));
                newTask = new Event(descriptor, from, to);
                break;
            }
            default:
                throw new IndexOutOfBoundsException();
            }
            tasks.add(newTask);
            tasks.get(tasks.size()-1).setIsDone(status);
        }
        numTasks = tasks.size();
        Ui.printMessage(Ui.CommandType.LIST);
    }

    /**
     * Adds a new task into the list of tasks.
     * @param userInput Command given by user to execute.
     *                  Should include the type of task and
     *                  task description in the correct format.
     * @throws DukeException if the user input to add task is invalid.
     */
    public static void addTask(String userInput) throws DukeException {
        Task task;
        String[] words = userInput.split(" ");
        validTask(words);
        String descriptor = userInput.substring(userInput.indexOf(words[1]));
        switch (words[0]) {
        case "todo":
            task = new Todo(descriptor);
            Ui.printMessage(task, Ui.CommandType.TODO);
            break;
        case "deadline":
            String by = descriptor.split("/by ")[1];
            descriptor = descriptor.split("/by ")[0];
            task = new Deadline(descriptor, by);
            Ui.printMessage(task, Ui.CommandType.DEADLINE);
            break;
        case "event":
            String to = descriptor.split("/to ")[1];
            String from = descriptor.split(" /")[1];
            descriptor = descriptor.split("/")[0];
            task = new Event(descriptor, from, to);
            Ui.printMessage(task, Ui.CommandType.EVENT);
            break;
        default:
            throw new DukeException();
        }
        tasks.add(task);
        numTasks = tasks.size();
    }

    /**
     * Delete a task from the list using its index.
     * @param userInput Command by user to execute.
     *                  Should include the index of the task.
     * @throws IndexOutOfBoundsException if index of task user puts is out of bounds.
     * @throws NumberFormatException if user did not input index of task.
     */
    public static void deleteTask(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        String taskNum = userInput.substring(userInput.length()-1);
        if (taskNum == null) {
            throw new NumberFormatException();
        }
        try {
            int taskNumber = Integer.parseInt(taskNum);
            if (tasks.get(taskNumber - 1) == null || tasks.size() == 0) {
                throw new IndexOutOfBoundsException();
            }
            Task temp = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            numTasks = tasks.size();
            Ui.printMessage(temp, Ui.CommandType.DELETE);
        } catch (NumberFormatException e) {
            Ui.printMessage(Ui.CommandType.NUMBERFORMAT);
        }
    }

    /**
     * Finds tasks using keywords that the user inputs.
     * @param query The keywords that the user wants to search for.
     * @throws IndexOutOfBoundsException if the list of tasks is empty.
     */
    public static void findTasks(String query) throws IndexOutOfBoundsException {
        if (tasks.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        for (Task task : tasks) {
            if (task.description.contains(query)) {
                System.out.println(task);
            }
        }
    }
}
