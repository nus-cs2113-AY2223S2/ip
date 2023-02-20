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


    public String extractCommand() {
        String[] extractFirstWord = userInput.split(" ", 2);
        return extractFirstWord[0];
    }

    public String extractCommandInfo() {
        String[] extractFirstWord = userInput.split(" ", 2);
        this.commandInfo = extractFirstWord[1];
        return extractFirstWord[1];
    }

    public String extractTaskName() {
        int index = commandInfo.indexOf("/by");
        String taskName = commandInfo.substring(0, index);
        return taskName;
    }

    public String extractTaskDeadline() {
        indexOfBy = commandInfo.indexOf("/by");
        String taskDeadline = commandInfo.substring(indexOfBy + 4);
        return taskDeadline;
    }

    public String extractEventName(String separator) {
        int indexOfFrom = commandInfo.indexOf("/from");
        int indexOfTo = commandInfo.indexOf("/to");
        this.indexOfFrom = indexOfFrom;
        this.indexOfTo = indexOfTo;
        String eventName = commandInfo.substring(0, indexOfFrom);
        return eventName;
    }

    public String extractEventStartDetails() {
        String eventStartDetails = commandInfo.substring(indexOfFrom + 6, indexOfTo - 1);
        return eventStartDetails;
    }

    public String extractEventEndDetails() {
        String eventEndDetails = commandInfo.substring(indexOfTo + 4);
        return eventEndDetails;
    }
}
