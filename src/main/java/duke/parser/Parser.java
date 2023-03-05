package duke.parser;

public class Parser {
    protected String command;
    protected String keyword;
    protected String description;
    protected String deadline;
    protected String fromDate;
    protected String toDate;
    protected String taskNumber;
    protected boolean shouldExit;

    public Parser(String inputText) {
        int dividerPosition = inputText.indexOf(" ");
        String commandWord = filterCommand(inputText);
        command = commandWord;
        switch (commandWord) {
            case "bye":
                shouldExit = true;
                break;
            case "list":
                shouldExit = false;
                break;
            case "mark":
                taskNumber = inputText.substring(dividerPosition + 1, inputText.length());
                shouldExit = false;
                break;
            case "unmark":
                taskNumber = inputText.substring(dividerPosition + 1, inputText.length());
                shouldExit = false;
                break;
            case "todo":
                description = filterDescription(inputText);
                shouldExit = false;
                break;
            case "deadline":
                description = filterDescriptionAndDeadline(inputText)[0];
                deadline = filterDescriptionAndDeadline(inputText)[1];
                shouldExit = false;
                break;
            case "event":
                description = filterDescriptionAndTimePeriod(inputText)[0];
                fromDate = filterDescriptionAndTimePeriod(inputText)[1];
                toDate = filterDescriptionAndTimePeriod(inputText)[2];
                shouldExit = false;
                break;
            case "delete":
                taskNumber = inputText.substring(dividerPosition + 1, inputText.length());
                shouldExit = false;
                break;
            case "find":
                keyword = filterDescription(inputText);
                shouldExit = false;
            default:
        }
    }

    private static String filterCommand(String sentence) {
        String[] words = sentence.split(" ");  // splits into words
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
        return sentence;
    }

    public static String filterDescription(String sentence) {
        String command = filterCommand(sentence);
        String description = sentence.replaceAll(command, "");
        return description.trim();
    }

    // Method to remove the command word, deadline and return the description
    public static String[] filterDescriptionAndDeadline(String sentence) {
        String[] output = new String[2];
        String str = filterDescription(sentence);
        int dividerPosition = str.indexOf("/");
        String description = str.substring(0, dividerPosition - 1);
        output[0] = description.trim();
        String deadline = str.substring(dividerPosition + 1, str.length());
        String byDate = deadline.replaceAll("by", "");
        output[1] = byDate.trim();
        return output;
    }

    public static String[] filterDescriptionAndTimePeriod(String sentence) {
        String[] output = new String[3];
        String str = filterDescription(sentence);
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
