/**
 * Represents messages printed by Duke for interactions with the user.
 */
public class Ui {
    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Greets the user at the start of the program.
     */
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Bids the user farewell when they exit the program.
     */
    public void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

    /**
     * Prints a message to the user when the user command is unidentified.
     */
    public void handleUnidentifiedCommand() {
        printLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means.");
        printLine();
    }

    /**
     * Prints error message when NumberFormatException is caught when attempting to
     * mark a task as done or undone, or while deleting a task.
     */
    public void handleNumberFormatException() {
        System.out.println("☹ OOPS!!! Task number is invalid. Please try again.");
        printLine();
    }

    /**
     * Prints error message when IndexOutOfBoundsException is caught when attempting to
     * mark a task as done or undone, or while deleting a task.
     */
    public void handleIndexOutOfBoundsException() {
        System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
        printLine();
    }

    /**
     * Prints error message when DukeException is caught when attempting to
     * mark a task as done or undone, or while deleting a task.
     */
    public void handleDukeException() {
        System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
        printLine();
    }

    /**
     * Prints error message when there is a missing task description for a To-do, Deadline
     * or Event task or a missing attribute such as the due date for a Deadline or a start
     * and end date/time for an Event.
     */
    public void printErrorMessage() {
        printLine();
        System.out.println("☹ OOPS!!! There's something missing in your task description.");
    }

    /**
     * Prints out the correct format for adding a To-do task.
     */
    public void printTodoFormat() {
        System.out.println("Please follow this format: todo [description].");
        printLine();
    }

    /**
     * Prints out the correct format for adding a Deadline task.
     */
    public void printDeadlineFormat() {
        System.out.println("Please follow this format: deadline [description] /by [due date/time]");
        printLine();
    }

    /**
     * Prints out the correct format for adding an Event task.
     */
    public void printEventFormat() {
        System.out.println("Please follow this format: event [description] /from [start] /to [end]");
        printLine();
    }
}
