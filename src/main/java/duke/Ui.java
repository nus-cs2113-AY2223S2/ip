package duke;


import java.util.Scanner;

public class Ui {
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    public static final String GREET_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String MARK_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";

    public static final String FIND_MESSAGE = "Here are the matching tasks in your list:";
    public static final String LINE = "____________________________________________________________\n";
    public static final String LS = System.lineSeparator();

    private Scanner in;

    /**
     * Constructor of the Ui class where it initialises the new Scanner which is in charge of taking user inputs
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Returns the line of String that has just been entered by the user
     *
     * @return a String as the command made by the user
     */
    public String readCommand() {
        String command = in.nextLine();
        return command;
    }

    /**
     * Outputs the find task command's opening message
     */
    public static void findTaskByKeywordOpeningMessage() {
        System.out.println(LINE + FIND_MESSAGE);
    }

    /**
     * Outputs a row of underscores representing a line
     */
    public static void printLine() {
        System.out.print(LINE);
    }

    /**
     * Outputs a message when a new task is being added corresponding to the task type
     *
     * @param tasks the TaskList to be referenced from after adding a new task
     */
    public static void addSpecialTaskMessage(TaskList tasks) {
        int lastElement = tasks.getTaskCount() - 1;
        System.out.println(LINE + tasks.getTaskFromIndex(lastElement).addTaskMessage() + "Now you have " +
                (lastElement + 1) + " tasks in the list." + System.lineSeparator() + LINE);
    }

    /**
     * Outputs a message that the description of the task is empty
     *
     * @param taskType the String of corresponding to the task type
     */
    public static void emptyCommandMessage(String taskType) {
        System.out.println(LINE + " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n" + LINE);
    }

    /**
     * Shows the user that the program does not understand what the user input is
     */
    public static void illegalCommandMessage() {
        System.out.println(LINE + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
    }

    /**
     * Prints the entire TaskList of tasks to the user
     *
     * @param tasks the TaskList to be printed
     */
    public static void printList(TaskList tasks) {
        System.out.print(LINE);
        for (int i = 0; i < tasks.getTaskCount(); ++i) {
            System.out.println((i + 1) + "." + tasks.getTaskFromIndex(i).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Greets the user when the program starts running
     */
    public static void greeting() {
        System.out.println(LINE + GREET_MESSAGE);
    }

    /**
     * Shows the user a message that the Task has just been deleted
     *
     * @param deleteIndex the index of the task in the TaskList that has been deleted
     * @param tasks       the TaskList to be referenced from
     */
    public static void deleteTaskMessage(int deleteIndex, TaskList tasks) {
        System.out.println(LINE + tasks.getTaskFromIndex(deleteIndex).deleteTaskMessage() + "Now you have "
                + (tasks.getTaskCount() - 1) + " tasks in the list." + System.lineSeparator() + LINE);
    }

    /**
     * The failure message when updating database has failed
     */
    public static void updateDatabaseFailureMessage() {
        System.out.println("Update database failure");
    }

    /**
     * Shows the user goodbye after the user wants to exit the program by typing bye
     */
    public static void goodbyeMessage() {
        System.out.print(LINE + EXIT_MESSAGE + LINE);
    }

    /**
     * Shows the user a message whenever the user marks or unmarks a task
     *
     * @param command        The full command String entered by the user
     * @param indexOfMarking the index that the user wants to mark or unmark
     * @param tasks          the TaskList to be referenced from for marking or unmarking of Task
     */
    public static void markChangeMessage(String command, int indexOfMarking, TaskList tasks) {
        System.out.print(LINE);
        if (command.startsWith("mark ")) {
            System.out.println(MARK_MESSAGE);
        } else if (command.startsWith("unmark ")) {
            System.out.println(UNMARK_MESSAGE);
        } else {
            System.out.println("failed to mark");
            return;
        }
        System.out.println("  " + tasks.getTaskFromIndex(indexOfMarking).toString() + LS + LINE);
    }
}
