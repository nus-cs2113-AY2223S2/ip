package duke;

public class Parser {

    public static int getIndex(String userCommand, String type) {
        switch (type) {
        case "task":
            return Integer.parseInt(userCommand.split(" ")[1])-1;
        case "by":
            return userCommand.indexOf("/by");
        case "from":
            return userCommand.indexOf("/from");
        case "to":
            return userCommand.indexOf("/to");
        }
        return 0;
    }

    public static String extractInfo(String userCommand, String type, String task) {
        switch (type) {
        case "desc":
            switch (task) {
            case "todo":
                return userCommand.substring(5);
            case "deadline":
                int indexOfBy = Parser.getIndex(userCommand, "by");
                return userCommand.substring(9, indexOfBy - 1);
            case "event":
                int indexOfFrom = Parser.getIndex(userCommand, "from");
                return userCommand.substring(6,indexOfFrom-1);
            }
        case "by":
            int indexOfBy = Parser.getIndex(userCommand, "by");
            return userCommand.substring(indexOfBy + 4);
        case "from":
            int indexOfFrom = Parser.getIndex(userCommand, "from");
            int indexOfTo1 = Parser.getIndex(userCommand, "to");
            return userCommand.substring(indexOfFrom+6,indexOfTo1-1);
        case "to":
            int indexOfTo2 = Parser.getIndex(userCommand, "to");
            return userCommand.substring(indexOfTo2+4);
        }

        return "";
    }

}
