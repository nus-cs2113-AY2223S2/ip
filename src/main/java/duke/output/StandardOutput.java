package duke.output;

public enum StandardOutput {
    SEGMENT_LINE("_".repeat(80)),
    START_MESSAGE(" Hello! I'm Duke" + System.lineSeparator() + " What can I do for you?"),
    BYE_MESSAGE(" Bye! Hope to see you again soon!"),
    EMPTY_LIST_MESSAGE(" The list is empty!"),
    LIST_MESSAGE(" Here are the tasks in your lists:"),
    MARK_MESSAGE(" Awesome! I've marked this task as done:"),
    UNMARK_MESSAGE(" OK, I've marked this task as not done yet:"),
    ADD_MESSAGE(" The following task has been added:" + System.lineSeparator()
            + "   %s" + System.lineSeparator() + " There is now %d task[s] in total." + System.lineSeparator()),
    INVALID_MARK_MESSAGE(" Invalid input! Valid input format: \"mark <number>\"!"),
    INVALID_UNMARK_MESSAGE(" Invalid input! Valid input format: \"unmark <number>\"!"),
    INVALID_INPUT_MESSAGE(" Invalid input! Please provide a valid input!"),
    INVALID_TODO_MESSAGE(" Invalid input! Valid input format: \"todo <task name>\""),
    MISSING_DEADLINE_KEYWORD_MESSAGE(" Invalid input! Valid input format: \"deadline <task name> /by <date>\""),
    INSUFFICIENT_DEADLINE_FIELD_MESSAGE(" Invalid input! Please provide enough arguments! "
            + System.lineSeparator() + " Valid input format: \"deadline <task name> /by <date>\""),
    INVALID_EVENT_FORMAT_MESSAGE(" Invalid input! Valid input format: \"event <task name> /from <date> /to <date>\""),
    INSUFFICIENT_EVENT_FIELD_MESSAGE(" Invalid input! Please provide enough arguments! " + System.lineSeparator()
            + " Valid input format: \"event <task name> /from <date> /to <date>\"");
    public final String STANDARD_OUTPUT;
    StandardOutput(String standardOutput) {
        STANDARD_OUTPUT = standardOutput;
    }
}
