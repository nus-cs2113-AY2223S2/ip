import java.util.Scanner;

/**
 * Class which helps to facilitate interactions with the user and improve user experience.
 */
public class Ui {
    private static final String DIVIDER  = "______________________________";
    public static void printLogo() {
        String logoMessage = "Hello from\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|";
        System.out.println(logoMessage);
    }

    public static void greetUser() {
        String greeting = DIVIDER + System.lineSeparator() + "Hello! I'm Jarvis!"
                + System.lineSeparator() + "What can I do for you?"
                + System.lineSeparator() + DIVIDER + System.lineSeparator();
        System.out.print(greeting);
    }

    public static void sayByeToUser() {
        String bye = DIVIDER + System.lineSeparator() + "Bye. Hope to see you again soon!"
                + System.lineSeparator() + DIVIDER + System.lineSeparator();
        System.out.println(bye);
    }

    /**
     * Prints the error message for when user does not specify a proper task type.
     */
    public static void unknownTaskError() {
        System.out.println(DIVIDER + System.lineSeparator()
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + System.lineSeparator() + DIVIDER);
    }

    /**
     * Prints the error message for when user keys in a string instead of a number for mark, unmark or delete commands.
     */
    public static void invalidTaskNumberError() {
        System.out.println(DIVIDER + System.lineSeparator()
                + "You did not key in a task number. Please key in a valid task number and try again!"
                + System.lineSeparator() + DIVIDER);
    }

    /**
     * Prints the error message for when user does not specify the description for a todo task.
     */
    public static void emptyDescriptionTodo() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a todo cannot be empty."
                + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    /**
     * Prints the error message for when user does not specify the description for a event task.
     */
    public static void emptyDescriptionEvent() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a event cannot be empty."
                + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    /**
     * Prints the error message for when user does not specify the description for a deadline task.
     */
    public static void emptyDescriptionDeadline() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a deadline cannot be empty."
                + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    /**
     * Prints the error message for when user does not specify the keyword for finding a matching task.
     */
    public static void emptyDescriptionKeyword() {
        String errorMessage = DIVIDER + System.lineSeparator()
                + "☹ OOPS!!! You did not specify a keyword.";
        System.out.println(errorMessage);
    }

    /**
     * Prints the error message for when user does not specify the task number for either
     * mark, unmark or delete tasks.
     */
    public static void emptyDescriptionNumber() {
        String errorMessage = DIVIDER + System.lineSeparator()
                + "☹ OOPS!!! The task number cannot be empty."
                + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    /**
     * Prints the error message for when user tries to mark a task which has already been marked.
     */
    public static void taskAlreadyMarkedError() {
        String errorMessage = DIVIDER + System.lineSeparator()
                + "This task has already been marked!" + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    /**
     * Prints the error message for when user tries to unmark a task which was
     * already unmarked initially.
     */
    public static void taskAlreadyNotMarkedError() {
        String errorMessage = DIVIDER + System.lineSeparator()
                + "This task was already unmarked!" + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    /**
     * Command to keep receiving user commands until condition is reached.
     * @return the user's commands.
     */
    public static String getUserCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
