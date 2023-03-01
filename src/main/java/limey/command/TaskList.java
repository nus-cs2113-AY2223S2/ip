package limey.command;

import limey.exception.commandNotFoundException;
import limey.exception.invalidDateException;
import limey.iohandler.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    /**
     * Attempts to unmark a given task, if it fails, will print the relevant error message
     *
     * @param tasks the list of current tasks
     * @param wordList string array of individual words given by the user input
     */
    public static void printUnmarkTask(ArrayList<Task> tasks, String[] wordList) {
        try {
            unmarkTask(tasks, wordList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Ui.invalidMessage("Index out of bounds.");
        } catch (NumberFormatException e) {
            Ui.invalidMessage("Index given is not a number.");
        }
    }
    /**
     * Unmarks a given task
     *
     * @param tasks the list of current tasks
     * @param wordList string array of individual words given by the user input, whereby index 1 item is the list index of the task to unmark
     */
    private static void unmarkTask(ArrayList<Task> tasks, String[] wordList) {
        String inLine;
        int taskIndex;
        inLine = wordList[1];
        taskIndex = Integer.parseInt(inLine) - 1;
        tasks.get(taskIndex).setDone(false);
        Ui.printUnmarked(tasks.get(taskIndex));
    }

    /**
     * Attempts to mark a given task, if it fails, will print the relevant error message
     *
     * @param tasks the list of current tasks
     * @param wordList string array of individual words given by the user input
     */
    public static void printMarkTask(ArrayList<Task> tasks, String[] wordList) {
        try {
            markTask(tasks, wordList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Ui.invalidMessage("Index out of bounds.");
        } catch (NumberFormatException e) {
            Ui.invalidMessage("Index given is not a number.");
        }
    }
    /**
     * Attempts to delete a given task, if it fails, will print the relevant error message
     *
     * @param tasks the list of current tasks
     * @param wordList string array of individual words given by the user input
     */
    public static void printDeleteTask(ArrayList<Task> tasks, String[] wordList) {
        try {
            deleteTask(tasks, wordList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Ui.invalidMessage("Index out of bounds.");
        } catch (NumberFormatException e) {
            Ui.invalidMessage("Index given is not a number.");
        }
    }
    /**
     * Deletes a given task
     *
     * @param tasks the list of current tasks
     * @param wordList string array of individual words given by the user input, whereby index 1 item is the list index of the task to delete
     */
    private static void deleteTask(ArrayList<Task> tasks, String[] wordList) {
        String inLine;
        int taskIndex;
        inLine = wordList[1];
        taskIndex = Integer.parseInt(inLine) - 1;
        Ui.printDeleteTask(tasks.get(taskIndex));
        tasks.remove(taskIndex);
        Task.numTasks--;
    }
    /**
     * Marks a given task
     *
     * @param tasks the list of current tasks
     * @param wordList string array of individual words given by the user input, whereby index 1 item is the list index of the task to mark
     */
    private static void markTask(ArrayList<Task> tasks, String[] wordList) {
        int taskIndex;
        String inLine;
        inLine = wordList[1];
        taskIndex = Integer.parseInt(inLine) - 1;
        tasks.get(taskIndex).setDone(true);
        Ui.printMarked(tasks.get(taskIndex));
    }
    /**
     * Creates and adds a new task with the corresponding name
     * to the current tasks arraylist
     *
     * @param tasks the full array list of current tasks
     * @param inLine input line read from the user input on the command line interface
     */
    public static void makeNewTask(ArrayList<Task> tasks, String inLine, String firstWord) throws commandNotFoundException {
        Task taskIn;
        inLine = inLine.substring(inLine.indexOf(" ") + 1);
        switch (firstWord) {
        case "deadline":
            try {
                taskIn = new Deadline(inLine);
            } catch (invalidDateException | StringIndexOutOfBoundsException | DateTimeParseException e) {
                Ui.invalidMessage("Invalid deadline date, please format date as /by yyyy-mm-ddTHH:MM\n\tExample to set assignment deadline on 8th Feb 2023 at 11:59pm input the following \"deadline assignment /by 2023-02-08T23:59\"");
                return;
            }
            break;
        case "event":
            try {
                taskIn = new Event(inLine);
            } catch (invalidDateException | StringIndexOutOfBoundsException e) {
                Ui.invalidMessage("Invalid event date, please format date as /by yyyy-mm-ddTHH:MM\n\tExample to set exam on 8th Feb 2023 2:00-3:30pm, input the following \"event exam /from 2023-02-08T14:00 /to 2023-02-08T15:30\"");
                return;
            }
            break;
        case "todo": // currently default create a todo object
            taskIn = new Todo(inLine);
            break;
        default:
            throw new commandNotFoundException();
        }
        tasks.add(taskIn);
        Task.numTasks++;
        Ui.printAdded(taskIn, Task.numTasks);
    }
}
