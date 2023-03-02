package common;

/**
 * Define output messages to readers as constants.
 */
public class Messages {
    public static final String LINE = "________________________________________________________________\n";
    public static final String MESSAGE_WELCOME = LINE +
            "Hello! I'm Duke, your to-do list manager.\n" +
            "You can add tasks to your to-do list, mark them as done, view your entire to-do list and many more!\n" +
            "To get started, key in 'help' to check out all the functions and how to use them.\n" +
            LINE;
    public static final String MESSAGE_GOODBYE = "Thank you for using Duke. Hope to see you soon!";
    public static final String PRINT_TASK_HEADING = LINE + "Here are the tasks in your list:";
    public static final String TASK_TYPE_EXCEPTION_ERROR = "Sorry, Duke does not recognise the task." +
            "Type 'todo'/'deadline'/'event' to add task of respective type.\n" + LINE;

    public static final String EVENT_TIMING_EXCEPTION_ERROR =
            "Starting and ending time for event are in the wrong order. " + "Please try again.\n" + LINE;

    public static final String INSUFFICIENT_INPUT_ERROR = "Insufficient input. Adding task failed.\n" + LINE;
    public static final String NUMBER_FORMAT_EXCEPTION_ERROR =
            "Task number not specified. Please try again.\n" + LINE;
}
