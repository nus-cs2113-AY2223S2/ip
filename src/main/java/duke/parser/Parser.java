package duke.parser;

/**
 * parser class helps to extract relevant
 * information from user input
 */
public class Parser {

    protected String userInput;
    protected String commandInfo;
    protected String taskName;
    protected int indexOfBy;
    protected String taskDeadline;
    protected int indexOfFrom;
    protected int indexOfTo;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Extracts first word from user input which is the command
     *
     * @return the user command
     */
    public String extractCommand() {
        String[] extractFirstWord = userInput.split(" ", 2);
        return extractFirstWord[0];
    }

    /**
     * Stores rest of the command info
     *
     * @return remaining command info
     */
    public String extractCommandInfo() {
        String[] extractFirstWord = userInput.split(" ", 2);
        this.commandInfo = extractFirstWord[1];
        return extractFirstWord[1];
    }

    /**
     * Finds the name of the deadline task to be added
     *
     * @return name of deadline task
     */
    public String extractTaskName() {
        int index = commandInfo.indexOf("/by");
        String taskName = commandInfo.substring(0, index);
        return taskName;
    }

    /**
     * Finds deadline of task to be added
     *
     * @return deadline of task
     */
    public String extractTaskDeadline() {
        indexOfBy = commandInfo.indexOf("/by");
        String taskDeadline = commandInfo.substring(indexOfBy + 4);
        return taskDeadline;
    }

    /**
     * Finds the name of the event task to be added
     *
     * @return name of event task
     */
    public String extractEventName() {
        int indexOfFrom = commandInfo.indexOf("/from");
        int indexOfTo = commandInfo.indexOf("/to");
        this.indexOfFrom = indexOfFrom;
        this.indexOfTo = indexOfTo;
        String eventName = commandInfo.substring(0, indexOfFrom);
        return eventName;
    }

    /**
     * Finds start details of event task
     *
     * @return the start details of the event
     */
    public String extractEventStartDetails() {
        String eventStartDetails = commandInfo.substring(indexOfFrom + 6, indexOfTo - 1);
        return eventStartDetails;
    }

    /**
     * Finds end details of event task
     *
     * @return the end details of the event
     */
    public String extractEventEndDetails() {
        String eventEndDetails = commandInfo.substring(indexOfTo + 4);
        return eventEndDetails;
    }
}
