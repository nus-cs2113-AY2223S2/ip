package duke.ui;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Class to handle all ui outputs to the user
 */
public class Ui {

    /**
     * Prints out the greeting message to the user
     */
    public static void greetUser() {
        String logo = " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| | |_   ___ `.  | || ||_   _||_   _|| || | |_  ||_  _|  | || | |_   ___  |  | |\n" +
                "| |   | |   `. \\ | || |  | |    | |  | || |   | |_/ /    | || |   | |_  \\_|  | |\n" +
                "| |   | |    | | | || |  | '    ' |  | || |   |  __'.    | || |   |  _|  _   | |\n" +
                "| |  _| |___.' / | || |   \\ `--' /   | || |  _| |  \\ \\_  | || |  _| |___/ |  | |\n" +
                "| | |________.'  | || |    `.__.'    | || | |____||____| | || | |_________|  | |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------' ";
        System.out.println("Hello from\n" + logo);
        String greetMessage = "Hello! I'm Duke\n"
                + "Send me a list of things to remember!\n"
                + "Type <bye> to exit";
        System.out.println(greetMessage);
    }

    /**
     * Prints a separator line
     */
    public void showLine() {
        System.out.println("===================");
    }

    /**
     * Prints an error separator line with error message
     */
    public void errorMessage(String errorText) {
        System.out.println(errorText);
        System.out.println("xxxxxxxxxxxxxxxxx");
    }

    /**
     * Prints the details of added task deadline
     *
     * @param deadline newly added deadline task
     * @param tasks    taskList of all tasks
     */
    public void printTaskDeadline(Deadline deadline, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + "[D][ ] " + deadline.getDescription() + deadline.getDueBy() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints the details of added task event
     *
     * @param event newly added event task
     * @param tasks taskList of all tasks
     */
    public void printTaskEvent(Event event, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + "[E][ ] " + event.getDescription() + event.getDuration() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints the details of added task
     *
     * @param task  newly added task
     * @param tasks taskList of all tasks
     */
    public void printTaskTodo(Task task, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + "[T][ ]" + task.getDescription() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints the details of deleted task
     *
     * @param task  deleted task
     * @param tasks taskList of all tasks
     */
    public void printDeletedTask(Task task, TaskList tasks) {
        System.out.println("Noted sir, I have removed \n"
                + task.getTaskType() + "[ ]" + task.getDescription() + "\n"
                + "from the list");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints the details of marked task
     *
     * @param task task that has been marked
     */
    public void printMarkedTask(Task task) {
        System.out.println("Noted sir, I have marked \n"
                + task.getTaskType() + "[X] " + task.getDescription() + "\n"
                + "as done.");
    }

    /**
     * Prints the details of the unmarked task
     *
     * @param task task that has been unmarked
     */
    public void printUnmarkedTask(Task task) {
        System.out.println("Noted sir, I have marked \n"
                + task.getTaskType() + "[ ]" + task.getDescription() + "\n"
                + "as not done.");
    }

    /**
     * Prints all current tasks in taskList
     *
     * @param tasks taskList of all current tasks
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
     * Prints the sorted version of the taskList according to endTime
     *
     * @param storage handler to read write to json
     * @throws DukeException occurs when there is a read or write error
     */
    public void printSorted(Storage storage) throws DukeException {
        System.out.println("Sorting the list for you...");
        TaskList toSort = new TaskList(storage.setTasks()); //temp TaskList to sort and discard
        toSort.sortTaskList();
        printTasks(toSort);
    }

    /**
     * Sorts and saves the taskList which is printed to user
     *
     * @param tasks   taskList of all current tasks
     * @param storage handler to read write to json
     * @throws DukeException occurs when there is a read or write error
     */
    public void sortTasks(TaskList tasks, Storage storage) throws DukeException {
        System.out.println("Sorting and saving the list for you...");
        tasks.sortTaskList();
        printTasks(tasks);
        tasks.saveList(storage);
    }

    /**
     * Finds the tasks in taskList which their description contains query string and
     * prints out to the user
     *
     * @param tasks taskList of all current tasks
     * @param query query string to search for
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
