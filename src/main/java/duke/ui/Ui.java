package duke.ui;

import duke.tasks.TaskList;

/**
 * Class that contains methods to send messages to users depending on what they input.
 */
public class Ui {

    /**
     * String of underscores that represents a divider in the terminal.
     */
    public static final String LONG_LINE = "____________________________________________________________";

    /**
     * Prints out the long line, divider on a new line.
     */
    public static void printLine(){
        System.out.println(LONG_LINE);
    }

    /**
     * Displays a welcome message when program starts running.
     */
    public static void printGreetings (){
        printLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Displays an exit message when user exits the program by inputting 'bye'.
     */
    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Displays the current number of tasks in the task list.
     * @param listCount The current number of tasks in the list.
     */
    public static void printListMessage(int listCount) {
        System.out.println("Now you have " + Integer.toString(listCount) + " tasks in the list.");
    }

    /**
     * Displays a message to inform user that the task had been removed.
     */
    public static void deleteMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Displays the message when a task is being added into the list
     * and the updated number of tasks in the list.
     *
     * @param taskList The task list that the task was added to, to be referenced for the
     *                 number of tasks and the task added.
     */
    public static void printAddedTaskCommand(TaskList taskList) {
        int lastElement = taskList.listCount() - 1;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.getTask(lastElement).toString());
        System.out.println("Now you have " + Integer.toString(taskList.listCount()) + " tasks in the list.");
    }

    /**
     * Displays a message to inform user that the task has been marked.
     *
     * @param taskList The task list to be referenced from for the marking of the task.
     * @param index The index of task in the task list that the user wants to mark.
     */
    public static void markDoneMessage(TaskList taskList, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTask(index).toString());
    }

    /**
     * Displays a message to inform user that the task marking has been removed.
     *
     * @param taskList The task list to be referenced from for the removing of the mark of the task.
     * @param index The index of task in the task list that the user wants to unmark.
     */
    public static void markUndoneMessage(TaskList taskList, int index){
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.getTask(index).toString());
    }

    /**
     * Displays an error message when user inputs an invalid command.
     */
    public static void printInvalidMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Displays an error message when user inputs an empty command.
     *
     * @param command The string for the corresponding task being inputted.
     */
    public static void printEmptyCommandMessage(String command) {
        System.out.println("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }

    /**
     * Displays an error message when user inputs an index that is out of bounds or does not exist.
     */
    public static void printInvalidIndexMessage() {
        System.out.println("No such task exist! Try again.");
    }
}
