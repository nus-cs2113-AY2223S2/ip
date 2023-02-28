package duke.outputs;
/**
 * Class containing the messages to print to user based on commands and inputs
 */
public class Messages {
    // This block is for UI section
    public static final String MARGIN = "*----------------------------*";
    public static final String WELCOME_MESSAGE_1 = "Hello! I'm Duke!" ;
    public static final String WELCOME_MESSAGE_2 = "What can I do for you?";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    //This block is for the possible errors that may occur during user inputs
    public static final String ERROR_MESSAGE_EMPTY_LIST = "The current task list is empty.";
    public static final String ERROR_MESSAGE_WRONG_TASK_INDEX = "Please specify a valid task number!";
    public static final String ERROR_MESSAGE_UNKNOWN_COMMAND = "☹ OOPS!!! I do not understand this command. :-(";
    public static final String ERROR_MESSAGE_EMPTY_TODO = "☹ OOPS!!! The description of todo cannot be empty.";
    public static final String ERROR_MESSAGE_EMPTY_DEADLINE = "☹ OOPS!!! The description of deadline cannot be empty.";
    public static final String ERROR_MESSAGE_EMPTY_EVENT = "☹ OOPS!!! The description of event cannot be empty.";
    public static final String ERROR_MESSAGE_EMPTY_DELETE = "Please specify a task number to delete!";
    public static final String ERROR_MESSAGE_EMPTY_MARK = "Please specify a task number to mark!";
    public static final String ERROR_MESSAGE_EMPTY_UNMARK = "Please specify a task number to unmark!";
    public static final String ERROR_MESSAGE_NON_NUMERICAL_INDEX_INPUT = "Please key in a valid numerical input as the task index!";
    public static final String ERROR_MESSAGE_KEYWORD_NOT_FOUND = "Oops! I could not find any task matching that keyword.";
    public static final String ERROR_MESSAGE_KEYWORD_UNSPECIFIED = "Please enter a keyword to for me to search.";
    public static final String ERROR_MESSAGE_FILE_IOEXCEPTION_ERROR = "☹ OOPS!!! The file is corrupted.";
    public static final String ERROR_MESSAGE_FILE_LOAD_ERROR = "There is an error during loading of the file.";

    // Some common error messages packaged into functions for easier calling
    public static void emptyTodoErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_EMPTY_TODO);
        System.out.println(MARGIN);
    }

    public static void emptyDeadlineErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_EMPTY_DEADLINE);
        System.out.println(MARGIN);
    }

    public static void emptyEventErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_EMPTY_EVENT);
        System.out.println(MARGIN);
    }

    public static void emptyDeleteErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_EMPTY_DELETE);
        System.out.println(MARGIN);
    }

    public static void emptyMarkErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_EMPTY_MARK);
        System.out.println(MARGIN);
    }

    public static void emptyUnmarkErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_EMPTY_UNMARK);
        System.out.println(MARGIN);
    }

    public static void emptyFindErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_KEYWORD_UNSPECIFIED);
        System.out.println(MARGIN);
    }

    public static void invalidDeleteErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_WRONG_TASK_INDEX);
        System.out.println(MARGIN);
    }

    public static void invalidMarkErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_WRONG_TASK_INDEX);
        System.out.println(MARGIN);
    }

    public static void invalidUnmarkErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_WRONG_TASK_INDEX);
        System.out.println(MARGIN);
    }

    public static void invalidTaskErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_WRONG_TASK_INDEX);
        System.out.println(MARGIN);
    }

    public static void unknownCommandErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_UNKNOWN_COMMAND);
        System.out.println(MARGIN);
    }

    public static void taskLoadErrorMessage(){
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_FILE_LOAD_ERROR);
        System.out.println(MARGIN);
    }

    public static void IOErrorMessage(){
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_FILE_IOEXCEPTION_ERROR);
        System.out.println(MARGIN);
    }

    public static void emptyListMessage(){
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_EMPTY_LIST);
        System.out.println(MARGIN);
    }

    public static void nonNumberInputErrorMessage(){
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_NON_NUMERICAL_INDEX_INPUT);
        System.out.println(MARGIN);
    }
}
