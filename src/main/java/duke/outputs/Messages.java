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

    //This block is for Parser section
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:" ;
    public static final String MESSAGE_TASK_LIST_SIZE = "Now you have %d tasks in the list." ;
    public static final String MESSAGE_MARK_TASK = "Ok! I've marked this task:";
    public static final String MESSAGE_UNMARK_TASK = "Ok! I've unmarked this task:";
    public static final String MESSAGE_DELETE_TASK = "Ok! I've deleted this task:";
    public static final String MESSAGE_KEYWORD_FOUND = "Here are the matching tasks in your list:";

    //This block is for the possible errors that may occur during user inputs
    public static final String ERROR_MESSAGE_WRONG_TASK_INDEX = "Please specify a valid task number!";
    public static final String ERROR_MESSAGE_UNKNOWN_COMMAND = "☹ OOPS!!! I do not understand this command. :-(";
    public static final String ERROR_MESSAGE_EMPTY_TODO = "☹ OOPS!!! The description of todo cannot be empty.";
    public static final String ERROR_MESSAGE_EMPTY_DEADLINE = "☹ OOPS!!! The description of deadline cannot be empty.";
    public static final String ERROR_MESSAGE_EMPTY_EVENT = "☹ OOPS!!! The description of event cannot be empty.";
    public static final String ERROR_MESSAGE_EMPTY_DELETE = "Please specify a task number to delete!";
    public static final String ERROR_MESSAGE_EMPTY_MARK = "Please specify a task number to mark!";
    public static final String ERROR_MESSAGE_EMPTY_UNMARK = "Please specify a task number to unmark!";
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

    public static void invalidDeleteErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_WRONG_TASK_INDEX);
        System.out.println(MARGIN);
    }

    public static void invalidMarkErrorMessage() {
        System.out.println(MARGIN);
        System.out.println(ERROR_MESSAGE_EMPTY_UNMARK);
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
}
