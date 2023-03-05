package duke.common;

/**
 * Container that contains all the messages being displayed to the user
 */
public class Messages {
    public static final String GOODBYE_MESSAGE = "Good bye! Hope to see you again soon.";
    public static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task";
    public static final String MARK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String MARK_DONE_MESSAGE = "OK, I've marked this task as completed:";
    public static final String LIST_TASKS_MESSAGE = "Here are the task(s) in your list:";
    public static final String INVALID_TODO_FORMAT_MESSAGE = "The description of a todo cannot be empty!";
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE = "The description of deadline and/or date of deadline cannot be empty!";
    public static final String INVALID_EVENT_FORMAT_MESSAGE = "The description of the event and/or period of it cannot be empty!";
    public static final String EMPTY_TO_FIELD_MESSAGE = "To field is empty!" ;
    public static final String CREATING_NEW_FILE = "It looks like you do not have a save file, creating save file 'duke_list.txt' for you.";
    public static final String LOADING_FILE_MESSAGE = "We have loaded your file and this is the current state of your list.";
    public static final String INVALID_TASK_NUMBER_MESSAGE = "This task number does not exist!";
    public static final String DUKE_EXCEPTION_MESSAGE = "I'm sorry I'm not sure what this means!";
    public static final String MATCHING_TASKS_MESSAGE = "Here's the list of task(s) in your list that matches your keyword: ";
    public static final String WELCOME_MESSAGE =  " ___       __   _______   ___       ________  ________  _____ ______   _______ \n" +
            "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\ \n" +
            "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|\n" +
            " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__\n" +
            "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\ \n" +
            "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\ \n"+
            "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______|\\|__|     \\|__|\\|_______| \n\n" +
            "Hi! I'm Mike, your personal assistant! How can I help you today? \n";
}
