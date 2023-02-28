package duke.outputs;
/**
 * Class containing the messages to print to user based on commands and inputs
 */
public class Messages {
    public static final String WELCOME_MESSAGE_1 = "Hello! I'm Duke!" ;
    public static final String WELCOME_MESSAGE_2 = "What can I do for you?";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:" ;
    public static final String MESSAGE_TASK_LIST_SIZE = "Now you have %d tasks in the list." ;
    public static final String MESSAGE_MARK_TASK = "Ok! I've marked this task:";
    public static final String MESSAGE_UNMARK_TASK = "Ok! I've unmarked this task:";
    public static final String MESSAGE_DELETE_TASK = "Ok! I've deleted this task:";
    public static final String ERROR_MESSAGE_MISSING_TASK_INDEX = "Please specify a valid task number!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Sorry! I do not understand this command. :-(";
    public static final String ERROR_MESSAGE_TASK_NOT_FOUND = "This task does not exist!";
    public static final String ERROR_MESSAGE_MISSING_TASK_DESCRIPTION = "Please key in a valid description of the task!";
    public static final String ERROR_MESSAGE_LOADING_TASK_ERROR = "Oops! Task loading error!";
    public static final String ERROR_MESSAGE_INVALID_DATETIME = "Oops! I do not understand the format of the date and time inputted.";
    public static final String MESSAGE_KEYWORD_FOUND = "Here are the matching tasks in your list:";
    public static final String ERROR_MESSAGE_KEYWORD_NOT_FOUND = "Oops! I could not find any task matching that keyword.";
    public static final String ERROR_MESSAGE_KEYWORD_UNSPECIFIED = "Please enter a keyword to for me to search.";

}
