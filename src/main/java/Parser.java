public class Parser {
    public static String[] parse(String input) {
        String[] commands = new String[10];
        input = input.trim();
        String[] inputs = input.split(" ");
        commands[0] = inputs[0];

        switch (commands[0]) {
        case "list":
            break;
        case "mark":
        case "unmark":
            commands[1] = inputs[1];
            break;
        case "todo":
            String description = input.split("todo ")[1];
            commands[1] = description;
            break;
        case "deadline":
            String removedKeyword = input.split("deadline ")[1];
            String[] splitString = removedKeyword.split(" /by ");
            description = splitString[0];
            String due = splitString[1];
            commands[1] = description;
            commands[2] = due;
            break;
        case "event":
            removedKeyword = input.split("event ")[1];
            splitString = removedKeyword.split(" /from ");
            description = splitString[0];
            splitString = splitString[1].split(" /to ");
            String start = splitString[0];
            String end = splitString[1];
            commands[1] = description;
            commands[2] = start;
            commands[3] = end;
            break;
        default:
            commands[0] = null;
        }
        return commands;
    }
}