package duke.utils;

public enum Message {
    LINE("_________________"),
    INFO_COMMANDS("Commands: list, mark, unmark, todo, deadline, event, delete, find, exit"),
    INFO_WELCOME("Hello! I'm Duke, your friendly remainder chatbot!" + System.lineSeparator() +
        "What can I do for you today? " + INFO_COMMANDS),
    INFO_EXIT("Bye. Thanks for using me!"),

    INFO_LIST("Here are the items in your list:"),
    INFO_FIND("Here are the matching items in your list:"),
    INFO_FIND_NONE("There are no items that match your keyword in the list."),

    INFO_MARK("Good job! I've marked this item as done:"),
    INFO_UNMARK("OK, I've marked this item as not done yet:"),

    INFO_ITEM_ADD("Got it. I've added this item:"),
    INFO_ITEM_DELETE("Got it. I've deleted this item:"),
    INFO_ITEM_COUNT("Now you have %d items in the list." + System.lineSeparator()),

    ERROR_INVALID_COMMAND("Please enter a valid command." + System.lineSeparator() + INFO_COMMANDS),
    ERROR_MARK_INVALID_PARAMETER("Please specify an integer."),
    ERROR_MARK_OUT_OF_BOUNDS("Invalid item."),
    ERROR_TODO_MISSING_PARAMETER("Parameters contain missing fields. Example: todo to eat"),
    ERROR_DEADLINE_MISSING_PARAMETER("Parameters contain missing fields. Example: deadline to eat /by 20-10-2020 18:00"),
    ERROR_EVENT_MISSING_PARAMETER("Parameters contain missing fields. Example: event to eat /from 20-10-2020 12:00 /to 20-10-2020 15:00"),
    ERROR_FIND_MISSING_PARAMETER("Please specify your keyword. Example: find eat"),

    SUCCESS_LOADED_FILE("Data file " + Constants.FILE_NAME + " has been loaded successfully."),
    WARNING_MISSING_FILE("Could not locate data file " + Constants.FILE_NAME + ". Using an empty list..."),
    ERROR_FILE_INPUT("Could not open data file! Perhaps, did you modify the file?" + System.lineSeparator() +
        "Duke application will be terminated."),
    ERROR_FILE_OUTPUT("Could not save data file!" + System.lineSeparator() +
        "Duke application will be terminated.");

    private final String message;

    private Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
