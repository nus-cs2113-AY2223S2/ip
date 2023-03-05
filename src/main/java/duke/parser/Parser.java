package duke.parser;

/**
 * Parses the user's input.
 */
public class Parser {

    protected String command;
    protected String keyword;
    protected String description;
    protected String deadline;
    protected String fromDate;
    protected String toDate;
    protected String taskNumber;
    protected boolean shouldExit;

    /**
     * Parses the user's input into different commands and stores its information.
     *
     * @param userInput full user input string.
     */
    public Parser(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        String commandWord = filterCommand(userInput);
        command = commandWord;
        switch (commandWord) {
            case "bye":
                shouldExit = true;
                break;
            case "list":
                shouldExit = false;
                break;
            case "mark":
                taskNumber = userInput.substring(dividerPosition + 1, userInput.length());
                shouldExit = false;
                break;
            case "unmark":
                taskNumber = userInput.substring(dividerPosition + 1, userInput.length());
                shouldExit = false;
                break;
            case "todo":
                description = filterDescription(userInput);
                shouldExit = false;
                break;
            case "deadline":
                description = filterDescriptionAndDeadline(userInput)[0];
                deadline = filterDescriptionAndDeadline(userInput)[1];
                shouldExit = false;
                break;
            case "event":
                description = filterDescriptionAndTimePeriod(userInput)[0];
                fromDate = filterDescriptionAndTimePeriod(userInput)[1];
                toDate = filterDescriptionAndTimePeriod(userInput)[2];
                shouldExit = false;
                break;
            case "delete":
                taskNumber = userInput.substring(dividerPosition + 1, userInput.length());
                shouldExit = false;
                break;
            case "find":
                keyword = filterDescription(userInput);
                shouldExit = false;
            default:
        }
    }

    /**
     * Filters out the command word from the user's input string.
     *
     * @param userInput full user input string.
     * @return command word based on the user's input.
     */
    private static String filterCommand(String userInput) {
        String[] words = userInput.split(" ");  // splits into words
        for (String word : words) {
            switch (word) {
                case "bye":
                    return "bye";
                case "list":
                    return "list";
                case "mark":
                    return "mark";
                case "unmark":
                    return "unmark";
                case "todo":
                    return "todo";
                case "deadline":
                    return "deadline";
                case "event":
                    return "event";
                case "delete":
                    return "delete";
                case "find":
                    return "find";
                default:
            }
        }
        return userInput;
    }

    /**
     * Filters out the description from the user's input string.
     *
     * @param userInput full user input string.
     * @return a string which is the description of the task based on the user's input.
     */
    public static String filterDescription(String userInput) {
        String command = filterCommand(userInput);
        String description = userInput.replaceAll(command, "");
        return description.trim();
    }

    /**
     * Filters out the description and the deadline date from the user's input string.
     *
     * @param userInput full user input string.
     * @return a string array containing the description and deadline of the task based on the user's input.
     */
    public static String[] filterDescriptionAndDeadline(String userInput) {
        String[] output = new String[2];
        String str = filterDescription(userInput);
        int dividerPosition = str.indexOf("/");
        String description = str.substring(0, dividerPosition - 1);
        output[0] = description.trim();
        String deadline = str.substring(dividerPosition + 1, str.length());
        String byDate = deadline.replaceAll("by", "");
        output[1] = byDate.trim();
        return output;
    }

    /**
     * Filters out the description and time period from the user's input string.
     *
     * @param userInput full user input string.
     * @return a string array containing the description and time period of the task based on the user's input in.
     */
    public static String[] filterDescriptionAndTimePeriod(String userInput) {
        String[] output = new String[3];
        String str = filterDescription(userInput);
        int dividerPosition = str.indexOf("/");
        String description = str.substring(0, dividerPosition - 1);
        output[0] = description.trim();
        String deadline = str.substring(dividerPosition + 1, str.length());
        String fromPeriod = deadline.replaceAll("from", "");
        String fromDate = fromPeriod.substring(0, fromPeriod.indexOf("/"));
        output[1] = fromDate.trim();
        String toPeriod = fromPeriod.substring(fromPeriod.indexOf("/") + 1, fromPeriod.length());
        String toDate = toPeriod.replaceAll("to", "");
        output[2] = toDate.trim();
        return output;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getFromDate() {
        return fromDate;
    }
    public String getToDate(){
        return toDate;
    }
    public String getTaskNumber(){
        return taskNumber;
    }
    public String getCommand(){
        return command;
    }
    public String getKeyword(){
        return keyword;
    }
}
