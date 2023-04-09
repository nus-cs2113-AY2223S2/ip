import java.util.Scanner;

/**
 * This class represents the user interface of the Duke program. It provides methods for displaying
 * different messages to the user and reading user input.
 */
public class UI {

    private Scanner scanner;

    /**
     * Constructs a new UI object with a new instance of Scanner for reading user input from the console.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from the console and returns it as a String.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the introduction message of the Duke program.
     */
    static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke your personal life assistant \n" + logo);
        System.out.println("What can I do for you today?");
    }

    /**
     * Prints the number of tasks in the tasks list.
     */
    static void printSize() {
        System.out.println("Now you have " + TasksList.tasks.size() + " tasks in your list");
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     */
    static void printAddMark() {
        System.out.println("You are crushing it, 1 task down!");
    }

    /**
     * Prints a message indicating that a task has been unmarked.
     */
    static void printUnmark() {
        System.out.println("I have unchecked it for you");
    }

    /**
     * Prints a message indicating that a task has been deleted from the tasks list.
     */
    static void printDeleteMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Prints the status and description of a task.
     *
     * @param i the index of the task in the tasks list
     */
    static void printMarking(int i) {
        System.out.println(String.format(" [%s] [%s] %s",
                TasksList.tasks.get(i).getTypeIcon(), TasksList.tasks.get(i).getStatusIcon(), TasksList.tasks.get(i).getDescription()));
    }

    /**
     * Prints the goodbye message of the Duke program.
     */
    static void printBye() {
        System.out.println("Bye see you again!");
    }

    /**
     * Prints an error message.
     *
     * @param error the error message to be displayed
     */
    public void showErrorMessage(String error) {
        System.out.println("Error: " + error);
    }

}
