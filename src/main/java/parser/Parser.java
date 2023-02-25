package parser;



public class Parser {
    private static final Integer MARK_LEN = 4;
    private static final Integer UNMARK_LEN = 6;
    private static final Integer DELETE_LEN = 6;
    private static final Integer _BY_LEN = 4;
    private static final Integer _FROM_LEN = 6;
    private static final Integer _TO_LEN = 4;
    private static final Integer FIND_LEN = 4;
    public Integer markTaskIndex(String command) {
        return Integer.parseInt(command.substring(MARK_LEN).trim());
    }

    public Integer unmarkTaskIndex(String command) {
        return Integer.parseInt(command.substring(UNMARK_LEN).trim());
    }

    public Integer deleteTaskIndex(String command) {
        return Integer.parseInt(command.substring(DELETE_LEN).trim());
    }

    public String keyWord(String command) {
        return command.contains(" ") ? command.split(" ")[0] : command;
    }

    public String deadlineDate(String command) {
        return command.substring(command.indexOf("/by") + _BY_LEN).trim();
    }

    public String eventStartTime(String command) {
        return command.substring(command.indexOf("/from") + _FROM_LEN, command.indexOf("/to")).trim();
    }

    public String eventEndTime(String command) {
        return command.substring(command.indexOf("/to") + _TO_LEN);
    }

    public String searchKeyWord(String command) {
        return command.substring(FIND_LEN).trim();
    }
}
