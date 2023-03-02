package parser;

import exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represent a Parser that parses commands.
 */
public class Parser {
    private static final int COMMAND_ARG = 0;
    private static final int FIRST_ARG = 1;
    private static final String SINGLE_SPACE = " ";

    private static ArrayList<String> splitInputString(String input) {
        input = input.trim();
        return new ArrayList<>(Arrays.asList(input.split(SINGLE_SPACE)));
    }

    private static String combineStrings(ArrayList<String> inputs, int start, int end) throws IndexOutOfBoundsException {
        if (end < start) {
            return null;
        }
        StringBuilder sentence = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sentence.append(inputs.get(i));
            sentence.append(SINGLE_SPACE);
        }
        return sentence.toString().trim();
    }

    private static int getIndexOfArg(String arg, String taskType, ArrayList<String> inputs) throws DukeException {
        int argIndex = inputs.indexOf(arg);
        if (argIndex == -1) {
            throw new DukeException("Missing " + arg + " argument for " + taskType + "!");
        }
        return argIndex;
    }

    private static String getArg(String arg, String taskType, ArrayList<String> inputs, int start, int end) throws DukeException {
        String argValue = combineStrings(inputs, start, end);
        if (argValue == null) {
            throw new DukeException("OOPS!!! The " + arg + " of a " + taskType + " cannot be empty.");
        }
        return argValue;
    }

    private static void parseSingleIntArgumentCommand(ArrayList<String> commands, ArrayList<String> inputs) throws DukeException {
        try {
            commands.add(inputs.get(FIRST_ARG));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing task number!");
        }
    }

    private static void parseTodoCommand(ArrayList<String> commands, ArrayList<String> inputs) throws DukeException {
        String description = getArg("description", "todo", inputs, 1, inputs.size() - 1);
        commands.add(description);
    }

    private static void parseDeadlineCommand(ArrayList<String> commands, ArrayList<String> inputs) throws DukeException {
        int byArgIndex = getIndexOfArg("/by", "Deadline", inputs);
        String description = getArg("description", "Deadline", inputs, 1, byArgIndex - 1);
        String due = getArg("/by", "Deadline", inputs, byArgIndex + 1, inputs.size() - 1);
        commands.add(description);
        commands.add(due);
    }

    private static void parseEventCommand(ArrayList<String> commands, ArrayList<String> inputs) throws DukeException {
        int fromArgIndex = getIndexOfArg("/from", "Event", inputs);
        int toArgIndex = getIndexOfArg("/to", "Event", inputs);
        String description = getArg("description", "Event", inputs, 1, fromArgIndex - 1);
        String start = getArg("/from", "Event", inputs, fromArgIndex + 1, toArgIndex - 1);
        String end = getArg("/to", "Event", inputs, toArgIndex + 1, inputs.size() - 1);
        commands.add(description);
        commands.add(start);
        commands.add(end);
    }

    private static void parseStringArgumentsCommand(String command, ArrayList<String> commands,
                                                    ArrayList<String> inputs) throws DukeException {
        switch(command) {
        case "todo":
            parseTodoCommand(commands, inputs);
            break;
        case "deadline":
            parseDeadlineCommand(commands, inputs);
            break;
        case "event":
            parseEventCommand(commands, inputs);
            break;
        }
    }

    /**
     * Parse an input string to resolve it into a command and its argument.
     *
     * @param input The input string to be parsed.
     * @return The command and its argument(s) as an ArrayList of Strings.
     */
    public static ArrayList<String> parse(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>();
        ArrayList<String> inputs = splitInputString(input);
        commands.add(inputs.get(COMMAND_ARG));
        switch (commands.get(COMMAND_ARG)) {
        case "list":
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            parseSingleIntArgumentCommand(commands, inputs);
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            parseStringArgumentsCommand(inputs.get(COMMAND_ARG), commands, inputs);
            break;
        }
        return commands;
    }
}