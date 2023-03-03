package duke.utils;

/**
 * A Ui object contains the strings and methods that application uses to interact with users.
 * Includes the error messages.
 */
public class Ui {
    public static String LINE = "────────────────────────────────────────────────────────────────────────\n";
    public static String GENERAL_ERROR_MESSAGE = LINE
            + "Invalid input. Please try again! (=O^O=)\n" + LINE;
    public static String INVALID_NUM_ERROR_MESSAGE = LINE
            + "The task number is out of bound. Please try again! (=O^O=)\n" + LINE;
    public static String EVENT_TIME_ERROR_MESSAGE = LINE
            + "There is no start and end time for the event. "
            + "Please try again by using the keywords /from and /to! (=O^O=)\n" + LINE;
    public static String DEADLINE_TIME_ERROR_MESSAGE = LINE
            + "There is no deadline for this task. "
            + "Please try again by using the keywords /by! (=O^O=)\n" + LINE;
    public static String LOGO = "  /\\_/\\  (\n" +
            " ( ^.^ ) _)\n" +
            "   \\\"/  (\n" +
            " ( | | )\n" +
            "(__d b__)";
    public static String GREETING = LINE
            + " Meow! I'm Ashy n(=^-^=)n\n"
            + " What can I do for you?\n"
            + LINE;

    public static String FAREWELL_MESSAGE =
            " Bye. Hope to see you again soon meow!\n" + LINE;
   public void printGreetingMessage() {
       System.out.println(LOGO);
       System.out.println(GREETING);
   }
    /**
     * A ui object contains the strings and methods that application uses to interact with users.
     */
   public void printFarewellMessage() {
       System.out.println(FAREWELL_MESSAGE);
   }
    public void printListTasksMessage() {
        System.out.println("Here is your list!");
    }
    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
    public void printGuide() {
        System.out.println(
                "User Guide" + System.lineSeparator()
                        + "list: get the list of all tasks" + System.lineSeparator()
                        + "todo: add a task a task without duration or deadline to the list" + System.lineSeparator()
                        + "deadline: add a task with deadline to the list" + System.lineSeparator()
                        + "event: add a task with both starting and ending time" + System.lineSeparator()
                        + "mark: mark a task as 'done'" + System.lineSeparator()
                        + "unmark: mark a task as 'not done'" + System.lineSeparator()
                        + "delete: delete a task from the list" + System.lineSeparator()
                        + "find: find tasks from the list that match the keyword" + System.lineSeparator()
        );
    }
}
