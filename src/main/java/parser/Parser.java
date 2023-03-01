package parser;


public class Parser {
    private static final Integer MARK_LEN = 4;
    private static final Integer UNMARK_LEN = 6;
    private static final Integer DELETE_LEN = 6;
    private static final Integer _BY_LEN = 4;
    private static final Integer _FROM_LEN = 6;
    private static final Integer _TO_LEN = 4;
    private static final Integer FIND_LEN = 4;

    /**
     * Get index of the task to be marked from user input.
     *
     * @param command user input as String
     * @return index of the task to be marked as done as Integer
     */
    public Integer markTaskIndex(String command) {
        return Integer.parseInt(command.substring(MARK_LEN).trim());
    }

    /**
     * Get index of the task to be unmarked from user input.
     *
     * @param command user input as String
     * @return index of the task to be unmarked as Integer
     */
    public Integer unmarkTaskIndex(String command) {
        return Integer.parseInt(command.substring(UNMARK_LEN).trim());
    }

    /**
     * Get the index of the task to be deleted from user input.
     *
     * @param command user input as String
     * @return index of the task to be deleted as Integer
     */
    public Integer deleteTaskIndex(String command) {
        return Integer.parseInt(command.substring(DELETE_LEN).trim());
    }

    /**
     * Extracts the first word of a command as the keyword.
     *
     * @param command user input as String
     * @return first word of the command indicating the type of command entered as String
     */
    public String keyWord(String command) {
        return command.contains(" ") ? command.split(" ")[0] : command;
    }

    /**
     * Extracts time/date of the deadline in a Deadline task.
     *
     * @param command user input as String
     * @return time/date of the deadline of a Deadline task as String
     */
    public String deadlineDate(String command) {
        return command.substring(command.indexOf("/by") + _BY_LEN).trim();
    }

    /**
     * Extracts starting time/date of an event.
     *
     * @param command user input as String
     * @return starting time/date of an event as String
     */
    public String eventStartTime(String command) {
        return command.substring(command.indexOf("/from") + _FROM_LEN, command.indexOf("/to")).trim();
    }

    /**
     * Extracts ending time/date of an event.
     *
     * @param command user input as String
     * @return ending time/date of an event as String
     */
    public String eventEndTime(String command) {
        return command.substring(command.indexOf("/to") + _TO_LEN);
    }

    /**
     * Extracts the keyword to be searched.
     *
     * @param command user input as String
     * @return the keyword to perform search on as String
     */
    public String searchKeyWord(String command) {
        return command.substring(FIND_LEN).trim();
    }
}
