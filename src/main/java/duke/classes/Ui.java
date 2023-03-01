package duke.classes;

/**
 * A class that handles user interface and displays messages to the user.
 */
public class Ui {

    /** A string representing Duke's logo. */
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Displays a welcome message to the user with Duke's logo */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "\n" + "Hello! I'm Duke\n" + "What can I do for you\n");

    }

    /** Displays a message to the user indicating that a file has been created */
    public void showFileCreated() {
        System.out.println("the file has been created");
    }

    /** Displays a message to the user indicating that a file already exists */
    public void showFileExists() {
        System.out.println("the file already exists");
    }

    /** Displays a message to the user indicating that the content of the file will be displayed */
    public void showFileContent() {
        System.out.println("This is the current content of the duke_list file, if any:");
    }

    /** Displays an error message to the user indicating that a file was not found */
    public void showFileNotFoundError() {
        System.out.println("File not found");
    }

    /** Displays a warning message to the user indicating that a task cannot be marked if it hasn't been created yet */
    public void showMarkTaskWarning() {
        System.out.println("You cannot mark a task that hasn't been made");
    }

    /** Displays a warning message to the user indicating that a task cannot be unmarked if it hasn't been created yet */
    public void showUnmarkTaskWarning() {
        System.out.println("You cannot unmark a task that hasn't been made");
    }

    /** Displays a warning message to the user indicating that a task cannot be deleted if it hasn't been created yet */
    public void showDeleteTaskWarning() {
        System.out.println("You cannot delete a task that hasn't been made");
    }

    /** Displays a message to the user indicating that the list of tasks will be displayed */
    public void showTasksMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    /** Displays a message to the user indicating the end of a file */
    public void showWelcomeEnd() {
        System.out.println("----------------------End of file----------------------\n" + "Please input your commands here: ");
    }

    /** Displays a farewell message to the user */
    public void showFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
