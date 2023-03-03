package Onandon.command;

/**
 * Representation of the parser to parse the string command.
 */
public class Parser {

    /**
     * Returns the Command class that contains the configuration for the command.
     * It parses the String command into Command class so that program can execute
     * it properly.
     *
     * @param fullCommand full string of the command given by the user.
     */
    public static Command parse(String fullCommand){
        String commandType = fullCommand.split(" ")[0];
        String description = "";
        String find = "";
        String by = "";
        String from = "";
        String to = "";
        int index = 0;

        int indexBy;
        int indexFrom;
        int indexTo;

        switch(commandType){
        case "todo":
            description = fullCommand.replaceAll("todo ", "");
            break;
        case "deadline":
            indexBy = fullCommand.indexOf("/by");
            description = fullCommand.substring(9, indexBy-1);
            by = fullCommand.substring(indexBy + 4);
            break;
        case "event":
            indexFrom = fullCommand.indexOf("/from");
            indexTo = fullCommand.indexOf("/to");
            description = fullCommand.substring(6, indexFrom-1);
            from = fullCommand.substring(indexFrom+6, indexTo-1);
            to = fullCommand.substring(indexTo+4);
            break;
        case "mark":
        case "unmark":
        case "delete":
            index = Integer.parseInt(fullCommand.split(" ")[1])-1;
            break;
        case "find":
            find = fullCommand.replaceAll("find ", "");
            break;
        case "list":
        case "exit":
            break;
        }

        return new Command(commandType, description, by, from, to, index, find);
    }
}
