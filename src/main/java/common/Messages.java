package common;

/**
 * Define output messages to readers as constants.
 */
public class Messages {
    public static final String LINE = "________________________________________________________________\n";
    public static final String MESSAGE_WELCOME = LINE +
            "Hello! I'm Duke.\n" +
            "You can specify the type of tasks by using " +
            "'todo' / 'deadline' / 'event' keyword before the task description.\n" +
            "You can keep track of deadlines by typing 'deadline' " +
            "followed by task description + '/by' to specify the time of the deadline.\n" +
            "You can also add events to the list by typing 'event' " +
            "followed by task description + '/from' + starting time of event + '/to' ending time of event.\n" +
            "Type 'list' to view the current todo list.\n" +
            "Type 'mark' and task event index to mark the task as done " +
            "and type 'unmark' and index to undo the task.\n" +
            "Type 'bye' to exit Duke.\n" +
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
