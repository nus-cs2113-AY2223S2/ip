package utility;

import tasks.Task;

/**
 * A class in charge of interacting with the user.
 * It also contains common messages or String literals that are commonly used throughout Duke.
 * All methods are public and static.
 */
public class Ui {
    private static final String DEFAULT_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "__________________________\n";
    private static final String GREETING_MESSAGE = "Hello! Do you need "
            + "anything from me?\n"
            + "Once my owner is more proficient in what he does, he will give me more functions!\n";
    private static final String LIST_OF_SUPPORTED_FUNCTIONS = "I am currently only able to do: \n "
            + "1)echo \n 2)todo\n 3)mark\n 4)unmark\n 5)deadline\n 6)event\n 7)delete\n 8)find\n"
            + "When you wish to exit, do tell me by typing : bye";
    private static final String DEFAULT_LIST_FORMATTING = ") ";
    private static final String DEFAULT_ACKNOWLEDGEMENT = "Done!";
    private static final String DEFAULT_TASK_ADDED_ACKNOWLEDGEMENT = "Got it. I've added this task:\n";
    private static final String DEFAULT_TASK_DELETED_ACKNOWLEDGEMENT = "Got it. I've removed this task\n";
    private static final String START_OF_USER_REMINDER = "Now you have ";
    private static final String END_OF_USER_REMINDER = " tasks in the list.";
    private static final String DEFAULT_FAILED_TO_FIND_MESSAGE = "I am sorry, but I did not find any matches.";
    private static final String DEFAULT_ERROR_MESSAGE = "Oh dear, Something has went wrong!";
    private static final String DEFAULT_EXIT_MESSAGE = "That's all from me! Goodbye!";
    public static final String DEFAULT_ECHO = "echo";
    public static final String DEFAULT_TODO = "todo";
    public static final String DEFAULT_EVENT = "event";
    public static final String DEFAULT_DEADLINE = "deadline";
    public static final String DEFAULT_MARK_TASK = "mark";
    public static final String DEFAULT_UNMARK_TASK = "unmark";
    public static final String DEFAULT_LIST_ALL_TASKS = "list";
    public static final String DEFAULT_DELETE = "delete";
    public static final String DEFAULT_FIND = "find";
    public static final String DEFAULT_EXIT = "bye";
    public static final String DEFAULT_LINE_SEPARATOR = " ";
    public static final String DEFAULT_FLAG_SEPARATOR = "/";

    /**
     * Prints the standard greeting for users to see.
     */
    public static void printGreetings() {
        Ui.print(DEFAULT_LOGO + GREETING_MESSAGE + LIST_OF_SUPPORTED_FUNCTIONS);
    }

    /**
     * A shorter printing method call that utilises System.out.print
     *
     * @param input String that contains what is going to be printed for the user.
     */
    public static void print(String input) {
        System.out.println(input);
    }

    /**
     * Prints an element of a list of Tasks with proper formatting.
     *
     * @param iterator The iterator or index or the list you are accessing.
     * @param action   The list of Tasks that the user wishes to do.
     */
    public static void printListElement(int iterator, Task action) {
        int correctListElementNumber = iterator + 1;
        Ui.print(correctListElementNumber + DEFAULT_LIST_FORMATTING + action.toString());
    }

    /**
     * Prints the acknowledgement message when a task is marked or unmarked.
     *
     * @param action The list of Tasks that the user wishes to do.
     */
    public static void printDoneMarkingTasks(Task action) {
        Ui.print(DEFAULT_ACKNOWLEDGEMENT + System.lineSeparator() + action.toString());
    }

    /**
     * It prints the acknowledgement after a task has been added.
     * It then reminds the user how many tasks are currently in the list.
     *
     * @param action        The list of Tasks that the user wishes to do.
     * @param actionCounter The current size of the list action.
     */
    public static void printAcknowledgement(Task action, int actionCounter) {
        Ui.print(DEFAULT_TASK_ADDED_ACKNOWLEDGEMENT + action.toString() + System.lineSeparator()
                + START_OF_USER_REMINDER + actionCounter + END_OF_USER_REMINDER);
    }

    /**
     * Prints the message containing the currently supported actions.
     */
    public static void printCurrentSupportedActions() {
        Ui.print(LIST_OF_SUPPORTED_FUNCTIONS);
    }

    /**
     * It prints the acknowledgement after a task has been deleted.
     * It then reminds the user how many tasks are currently in the list.
     *
     * @param action        The list of Tasks that the user wishes to do.
     * @param actionCounter The current size of the list action.
     */
    public static void printDeleteAcknowledgement(Task action, int actionCounter) {
        Ui.print(DEFAULT_TASK_DELETED_ACKNOWLEDGEMENT + action.toString() + System.lineSeparator()
                + START_OF_USER_REMINDER + actionCounter + END_OF_USER_REMINDER);
    }

    /**
     * It prints the acknowledgement after a relevant find/search is done successfully.
     */
    public static void printFindAcknowledgement() {
        Ui.print(DEFAULT_ACKNOWLEDGEMENT);
    }

    /**
     * It prints the acknowledgement after a relevant find/search is done but was unsuccessful.
     */
    public static void printCannotFindAcknowledgement() {
        Ui.print(DEFAULT_FAILED_TO_FIND_MESSAGE);
    }

    /**
     * It prints the acknowledgement after the exit command is called.
     * It also says goodbye to the user.
     */
    public static void printExitMessage() {
        Ui.print(DEFAULT_EXIT_MESSAGE);
    }

    /**
     * It prints the default error message when something has gone wrong.
     * It is used when the commandChecker fails to figure out what went wrong.
     */
    public static void printDefaultErrorMessage() {
        Ui.print(DEFAULT_ERROR_MESSAGE);
    }

}
