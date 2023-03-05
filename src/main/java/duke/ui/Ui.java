package duke.ui;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all ui outputs to the user.
 */
public class Ui {

    private Scanner input;

    private static final String SEPARATOR = "===================";
    private static final String BAD_SEPARATOR = "XXXXXXXXXXXXXXXXX";
    private static final String LOGO = " .----------------.  .----------------.  .----------------.  .----------------. \n" +
            "| | |_   ___ `.  | || ||_   _||_   _|| || | |_  ||_  _|  | || | |_   ___  |  | |\n" +
            "| |   | |   `. \\ | || |  | |    | |  | || |   | |_/ /    | || |   | |_  \\_|  | |\n" +
            "| |   | |    | | | || |  | '    ' |  | || |   |  __'.    | || |   |  _|  _   | |\n" +
            "| |  _| |___.' / | || |   \\ `--' /   | || |  _| |  \\ \\_  | || |  _| |___/ |  | |\n" +
            "| | |________.'  | || |    `.__.'    | || | |____||____| | || | |_________|  | |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------' ";

    /**
     * Constructs a new Ui class
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Scans for new user input and parse into String array containing.
     * command and command arguments.
     *
     * @return String array containing command and its arguments.
     */
    public String[] readInput() {
        String[] userCommand = new String[2];
        userCommand[0] = input.next().toLowerCase();
        if (userCommand[0].equals("bye")) {
            return userCommand;
        }
        userCommand[1] = input.nextLine();
        return userCommand;
    }

    /**
     * Prints out the greeting message to the user.
     */
    public static void greetUser() {
        System.out.println("Hello from\n" + LOGO);
        String greetMessage = "Hello! I'm Duke\n"
                + "Send me a list of things to remember!\n"
                + "Type <bye> to exit";
        System.out.println(greetMessage);
    }

    /**
     * Prints a separator line.
     */
    public void showLine() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints an error separator line with error message.
     */
    public void errorMessage(String errorText) {
        System.out.println(errorText);
        System.out.println(BAD_SEPARATOR);
    }

    /**
     * Prints the details of added task deadline.
     *
     * @param deadline Newly added deadline task.
     * @param tasks    TaskList of all tasks.
     */
    public void printTaskDeadline(Deadline deadline, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + deadline.getTaskType() + "[ ] " + deadline.getDescription() + deadline.getDueBy() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints the details of added task event.
     *
     * @param event Newly added event task.
     * @param tasks TaskList of all tasks.
     */
    public void printTaskEvent(Event event, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + event.getTaskType() + "[ ] " + event.getDescription() + event.getDuration() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints the details of added task.
     *
     * @param task  Newly added task.
     * @param tasks TaskList of all tasks.
     */
    public void printTaskTodo(Task task, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + task.getTaskType() + "[ ]" + task.getDescription() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints the details of deleted task.
     *
     * @param task  Deleted task.
     * @param tasks TaskList of all tasks.
     */
    public void printDeletedTask(Task task, TaskList tasks) {
        System.out.println("Noted sir, I have removed \n"
                + task.getTaskType() + "[ ]" + task.getDescription() + "\n"
                + "from the list");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints the details of marked task.
     *
     * @param task Task that has been marked.
     */
    public void printMarkedTask(Task task) {
        System.out.println("Noted sir, I have marked \n"
                + task.getTaskStatus() + " " + task.getDescription() + "\n"
                + "as done.");
    }

    /**
     * Prints the details of the unmarked task.
     *
     * @param task Task that has been unmarked.
     */
    public void printUnmarkedTask(Task task) {
        System.out.println("Noted sir, I have marked \n"
                + task.getTaskType() + "[ ] " + task.getDescription() + "\n"
                + "as not done.");
    }

    /**
     * Prints all current tasks in taskList.
     *
     * @param tasks TaskList of all current tasks.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list!");
            return;
        }
        ArrayList<Task> readableList = tasks.getReadableList();
        int count = 1;
        for (Task i : readableList) {
            System.out.println(count + ". " + i.getTaskStatus());
            count++;
        }
        System.out.println("Now you have " + readableList.size() + " tasks in the list.");
    }

    /**
     * Prints the sorted version of the taskList according to endTime.
     *
     * @param storage Handler to read write to json.
     * @throws DukeException Occurs when there is a read or write error.
     */
    public void printSorted(Storage storage) throws DukeException {
        System.out.println("Sorting the list for you...");
        TaskList toSort = new TaskList(storage.setTasks()); //temp TaskList to sort and discard
        toSort.sortTaskList();
        printTasks(toSort);
    }

    /**
     * Sorts and saves the taskList which is printed to user.
     *
     * @param tasks   TaskList of all current tasks.
     * @param storage Handler to read write to json.
     * @throws DukeException Occurs when there is a read or write error.
     */
    public void sortTasks(TaskList tasks, Storage storage) throws DukeException {
        System.out.println("Sorting and saving the list for you...");
        tasks.sortTaskList();
        printTasks(tasks);
        tasks.saveList(storage);
    }

    /**
     * Finds the tasks in taskList which their description contains query string and
     * prints out to the user.
     *
     * @param tasks TaskList of all current tasks.
     * @param query Query string to search for.
     */
    public void findQueryTasks(TaskList tasks, String query) {
        ArrayList<Task> readableList = tasks.getReadableList();
        int matches = 0;
        for (Task task : readableList) {
            if (task.getDescription().contains(query)) {
                matches += 1;
                System.out.println(matches + ". " + task.getTaskStatus());
            }
        }
        System.out.println("You have " + matches + " to your query of: " + query);
    }

}
