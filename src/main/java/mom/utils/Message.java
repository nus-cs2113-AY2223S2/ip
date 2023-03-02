package mom.utils;

public enum Message {
    LINE("_________________"),
    INFO_COMMANDS("Please don't forget these commands: list, mark, unmark, todo, deadline, event, delete, find, exit"),
    INFO_WELCOME("Your nagging mom is here." + System.lineSeparator() +
        "How many times should I remind you? " + INFO_COMMANDS),
    INFO_EXIT("Leaving your mom? :("),

    INFO_LIST("Here are the things I need to remind you again and again..."),
    INFO_FIND("How should I know what you are looking for? Anyway..."),
    INFO_FIND_NONE("I already told you, there's nothing I can find!!!"),

    INFO_MARK("Finally done this task? You could have done it earlier."),
    INFO_UNMARK("I thought you told me this task was done???"),

    INFO_ITEM_ADD("Ok!! But make sure you finish this task soon:"),
    INFO_ITEM_DELETE("Finally, something that you don't need me to remind again."),
    INFO_ITEM_COUNT("Now you have %d items that I need to remember..." + System.lineSeparator()),

    ERROR_INVALID_COMMAND("How can you forget the command?" + System.lineSeparator() + INFO_COMMANDS),
    ERROR_MARK_INVALID_PARAMETER("I need a number so that I know what task are you talking about!"),
    ERROR_MARK_OUT_OF_BOUNDS("Why are you giving me a number that I don't even know???"),
    ERROR_TODO_MISSING_PARAMETER("You are missing something!! Say something like: todo to eat"),
    ERROR_DEADLINE_MISSING_PARAMETER("You are missing something!! Say something like: deadline to eat /by 20-10-2020 18:00"),
    ERROR_EVENT_MISSING_PARAMETER("You are missing something!! Say something like: event to eat /from 20-10-2020 12:00 /to 20-10-2020 15:00"),
    ERROR_EVENT_DATE_BEFORE("Why are you asking me to remember a date (/to) that is before another date (/from)?"),
    ERROR_FIND_MISSING_PARAMETER("What are you exactly looking for??? Say something like: find eat"),

    SUCCESS_LOADED_FILE("Your mom's reminder file " + Constants.FILE_NAME + " has been loaded."),
    WARNING_MISSING_FILE("Could not locate your mom's reminder file " + Constants.FILE_NAME + "! Initialising a new reminder file."),
    ERROR_FILE_INPUT("Could not open your mom's reminder file! Perhaps, did you mess up her file?" + System.lineSeparator() +
        "Anyway, she won't talk to you for now."),
    ERROR_FILE_OUTPUT("Your mom could not remember what you said!" + System.lineSeparator() +
        "Anyway, you might want to restart.");

    private final String message;

    private Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
