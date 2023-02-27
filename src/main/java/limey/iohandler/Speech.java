package limey.iohandler;

import limey.command.Task;

import java.util.ArrayList;

public class Speech {
    /**
     * Prints the closing message for Limey
     */
    public static void sayBye() {
        System.out.println("\tBye! Hope to see you again soon. :)");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    /**
     * Prints the introduction message for Limey
     */
    public static void introMessage() {
        System.out.println("Hi there thanks for choosing to use Limey, your personal CLI task manager!.");
        helpMessage();
    }
    /**
     * Prints the valid commands and helpful examples as a guide for how to use Limey
     */
    public static void helpMessage() {
        printLine();
        System.out.println("\tThe following are some helpful commands on how to use Limey.");
        System.out.println("\tTo add a task(todo/deadline/event):");
        System.out.println("\t\t\"todo math\" OR \"deadline lab assignment /by 2023-02-08T23:59\" OR \"event exam /from 2023-02-08T14:00 /to 2023-02-08T15:30\"");
        System.out.println("\tTo find a task:");
        System.out.println("\t\t\"find lab assignment\" returns all tasks with \"lab assignment\" in the name");
        System.out.println("\tTo list all tasks:");
        System.out.println("\t\t\"list\" lists all tasks");
        System.out.println("\tTo mark a task:");
        System.out.println("\t\t\"mark 2\" to mark the second item on the list");
        System.out.println("\tTo un-mark a task:");
        System.out.println("\t\t\"unmark 2\" to un-mark the second item on the list");
        System.out.println("\tTo delete a task:");
        System.out.println("\t\t\"delete 2\" to delete the second item on the list");
        System.out.println("\tTo get help from this page again:");
        System.out.println("\t\t\"help\" will show all the helpful commands listed above");
        printLine();
    }
    public static void printErrorLine() {
        System.out.println("/////////////////////////////////////////////////////////////");
    }
    /**
     * Prints that a task has just been added successfully
     *
     * @param task the task that has just been added
     */
    public static void printAdded(Task task, int numTasks) {
        printLine();
        System.out.println("\t" + "added: " + task.getTaskName());
        System.out.println("\tTotal number of current tasks: " + numTasks);
        printLine();
    }
    /**
     * Prints that a task has just been marked successfully
     *
     * @param task the task that has just been marked
     */
    public static void printMarked(Task task) {
        printLine();
        System.out.println("\t" + "I have just marked this task as done:");
        System.out.println("\t" + task.getTaskIdentity());
        printLine();
    }
    /**
     * Prints that there was an error detected and its corresponding error message
     *
     * @param errorMessage relevant error message to inform the user of the problem
     */
    public static void invalidMessage(String errorMessage) {
        printErrorLine();
        System.out.println("\tSorry that was an invalid command. Error: " + errorMessage);
        printErrorLine();
    }
    /**
     * Prints that a task has just been unmarked successfully
     *
     * @param task the task that has just been unmarked
     */
    public static void printUnmarked(Task task) {
        printLine();
        System.out.println("\t" + "This task has been marked as not done yet:");
        System.out.println("\t" + task.getTaskIdentity());
        printLine();
    }
    /**
     * Prints all tasks in the task list
     *
     * @param taskList the array list of tasks to print out
     * @param numTasks the total number of tasks in the task list
     */
    public static void printTaskList(ArrayList<Task> taskList, int numTasks) {
        printLine();
        if (taskList.isEmpty()) {
            System.out.println("You currently have 0 tasks.");
        } else {
            for (int listNum = 1; listNum <= numTasks; listNum++) {
                System.out.println("\t" + listNum + "." + taskList.get(listNum - 1).getTaskIdentity());
            }
        }
        printLine();
    }
    /**
     * Prints that a task has just been deleted successfully
     *
     * @param task the task that has just been deleted
     */
    public static void printDeleteTask(Task task) {
        printLine();
        System.out.println("\t" + "This task has been deleted:");
        System.out.println("\t" + task.getTaskIdentity());
        printLine();
    }
    /**
     * Prints that no task with the given search term has been found
     *
     * @param searchTerm the search term to find in a given list
     */
    public static void printNoFoundTask(String searchTerm) {
        printLine();
        System.out.println("\t" + "No task name found containing "+ searchTerm);
        printLine();
    }
}
