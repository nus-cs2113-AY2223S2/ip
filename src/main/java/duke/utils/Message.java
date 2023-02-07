package duke.utils;

public enum Message {
    LINE("_________________"),
    INFO_WELCOME("Hello! I'm Duke, your friendly remainder chatbot!\n" +
        "What can I do for you today? " +
        "Commands: list, mark, unmark, remind, deadline, event, delete, exit"),
    INFO_EXIT("Bye. Thanks for using me!"),

    INFO_LIST("Here are the items in your shopping list:"),

    INFO_MARK("Good job! I've marked this item as done:"),
    INFO_UNMARK("OK, I've marked this item as not done yet:"),

    INFO_ITEM_ADD("Got it. I've added this item:"),
    INFO_ITEM_DELETE("Got it. I've deleted this item:"),
    INFO_ITEM_COUNT("Now you have %d items in the list.\n"),

    ERROR_INVALID_COMMAND("Please enter a valid command."),
    ERROR_MARK_INVALID_PARAMETER("Please specify an integer."),
    ERROR_MARK_OUT_OF_BOUNDS("Invalid item."),
    ERROR_REMIND_MISSING_PARAMETER("Parameters contain missing fields. Example: remind to eat"),
    ERROR_DEADLINE_MISSING_PARAMETER("Parameters contain missing fields. Example: deadline to eat /by tomorrow"),
    ERROR_EVENT_MISSING_PARAMETER("Parameters contain missing fields. Example: event to eat /from 2pm /to 4pm");

    private final String message;

    private Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
