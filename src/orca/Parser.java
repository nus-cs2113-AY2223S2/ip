package orca;

public class Parser {
    public CommandType findCommandType(String userInput) {
        if (userInput.equals("bye")) {
            return CommandType.BYE;
        } else if (userInput.equals("list")) {
            return CommandType.LIST;
        } else if (userInput.startsWith("mark")) {
            return CommandType.MARK;
        } else if (userInput.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (userInput.startsWith("todo")) {
            return CommandType.TODO;
        } else if (userInput.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            return CommandType.EVENT;
        } else if (userInput.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (userInput.startsWith("find")) {
            return CommandType.FIND;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    public int TaskNo(String userInput, int startIdx) throws OrcaException {
        try {
            return Integer.parseInt(userInput.substring(startIdx));
        } catch (NumberFormatException e) {
            throw new OrcaException("I cannot parse the integer.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new OrcaException("I cannot parse the integer.");
        }
    }
}
