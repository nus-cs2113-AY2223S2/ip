/**
 * General messages to be printed when there are errors
 */
public class Messages {
    public static final String MESSAGE_DOTTED_LINE = "____________________________________________________________";
    public static final String MESSAGE_INVALID_TASK = "☹ OOPS!!! The task index provided is invalid";
    public static final String MESSAGE_UNKNOWN_COMMAND = "☹ OOPS!!! The command is unknown";
    public static final String MESSAGE_EMPTY_TODO = "☹ OOPS!!! The description of todo cannot be empty";
    public static final String MESSAGE_EMPTY_DEADLINE = "☹ OOPS!!! The description of deadline cannot be empty";
    public static final String MESSAGE_EMPTY_EVENT = "☹ OOPS!!! The description of event cannot be empty";
    public static final String MESSAGE_EMPTY_DELETE = "☹ OOPS!!! The description of delete cannot be empty";
    public static final String MESSAGE_INVALID_DELETE = "☹ OOPS!!! The description of delete is invalid";
    public static final String MESSAGE_NOT_NUMBER_DELETE = "☹ OOPS!!! The description of delete is not a number";
    public static final String MESSAGE_EMPTY_MARK = "☹ OOPS!!! The description of mark cannot be empty";
    public static final String MESSAGE_INVALID_MARK = "☹ OOPS!!! The description of mark is invalid";
    public static final String MESSAGE_NOT_NUMBER_MARK = "☹ OOPS!!! The description of mark is not a number";
    public static final String MESSAGE_EMPTY_UNMARK = "☹ OOPS!!! The description of unmark cannot be empty";
    public static final String MESSAGE_INVALID_UNMARK = "☹ OOPS!!! The description of unmark is invalid";
    public static final String MESSAGE_NOT_NUMBER_UNMARK = "☹ OOPS!!! The description of unmark is not a number";
    public static final String MESSAGE_EMPTY_FIND = "☹ OOPS!!! The description of find cannot be empty";

    public static void invalidTaskMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_INVALID_TASK);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void unknownCommandMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_UNKNOWN_COMMAND);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void emptyTodoMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_EMPTY_TODO);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void emptyDeadlineMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_EMPTY_DEADLINE);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void emptyEventMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_EMPTY_EVENT);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void emptyDeleteMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_EMPTY_DELETE);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void invalidDeleteMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_INVALID_DELETE);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void notNumberDeleteMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_NOT_NUMBER_DELETE);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void emptyMarkMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_EMPTY_MARK);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void invalidMarkMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_INVALID_MARK);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void notNumberMarkMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_NOT_NUMBER_MARK);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void emptyUnmarkMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_EMPTY_UNMARK);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void invalidUnmarkMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_INVALID_UNMARK);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void notNumberUnmarkMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_NOT_NUMBER_UNMARK);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

    public static void emptyFindMessage() {
        System.out.println(MESSAGE_DOTTED_LINE);
        System.out.println(MESSAGE_EMPTY_FIND);
        System.out.println(MESSAGE_DOTTED_LINE);
    }

}
