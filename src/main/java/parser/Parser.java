package parser;

public class Parser {
    public Integer markTaskIndex(String command) {
        return Integer.parseInt(command.substring(4).trim());
    }

    public Integer unmarkTaskIndex(String command) {
        return Integer.parseInt(command.substring(6).trim());
    }

    public String keyWord(String command) {
        return command.contains(" ") ? command.split(" ")[0] : command;
    }

    public String deadlineDate(String command) {
        return command.substring(command.indexOf("/by") + 3).trim();
    }

    public String eventStartTime(String command) {
        return command.substring(command.indexOf("/from") + 6, command.indexOf("/to")).trim();
    }

    public String eventEndTime(String command) {
        return command.substring(command.indexOf("/to") + 4);
    }
}
