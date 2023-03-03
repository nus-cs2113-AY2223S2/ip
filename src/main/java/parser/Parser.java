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

    /**
     * Trim starting and ending whitespaces in input and split by single whitespace.
     *
     * @param input The input to split.
     * @return An ArrayList of String containing the split input.
     */
    private static ArrayList<String> splitInputString(String input) {
        input = input.trim();
        return new ArrayList<>(Arrays.asList(input.split(SINGLE_SPACE)));
    }

    /**
     * Combine the Strings in inputs from index start to index end into a single String.
     *
     * @param inputs The ArrayList of String containing the Strings to be combined.
     * @param start  The starting index.
     * @param end    The ending index.
     * @return A String resulting from combining the specified strings in inputs.
     * @throws IndexOutOfBoundsException if index argument exceeds the index range of inputs.
     */
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

    /**
     * Find the index of a parameter of a task in an ArrayList representing the inputs.
     *
     * @param param The parameter to find its index for.
     * @param taskType The type of the task of the inputs.
     * @param inputs   The inputs containing the command and its parameter.
     * @return The index of the parameter in inputs.
     * @throws DukeException if parameter is missing in inputs.
     */
    private static int getIndexOfParam(String param, String taskType, ArrayList<String> inputs) throws DukeException {
        int argIndex = inputs.indexOf(param);
        if (argIndex == -1) {
            throw new DukeException("Missing " + param + " argument for " + taskType + "!");
        }
        return argIndex;
    }

    /**
     * Get the argument of a task from the inputs.
     *
     * @param param    The parameter to find its argument for.
     * @param taskType The type of the task of the inputs.
     * @param inputs   The inputs containing the command and its argument.
     * @param start    The starting index of the argument.
     * @param end      The ending index of the argument.
     * @return The argument of the parameter.
     * @throws DukeException if argument for parameter is missing in inputs.
     */
    private static String getArg(String param, String taskType, ArrayList<String> inputs, int start, int end)
            throws DukeException {
        String argValue = combineStrings(inputs, start, end);
        if (argValue == null) {
            throw new DukeException("OOPS!!! The " + param + " of a " + taskType + " cannot be empty.");
        }
        return argValue;
    }

    /**
     * Parse an input command that has a single int parameter.
     *
     * @param commands The list of parsed commands.
     * @param inputs   The list of user input.
     * @throws DukeException if int parameter is missing.
     */
    private static void parseSingleIntArgumentCommand(ArrayList<String> commands, ArrayList<String> inputs)
            throws DukeException {
        try {
            commands.add(inputs.get(FIRST_ARG));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing task number!");
        }
    }

    /**
     * Parse a Find command.
     *
     * @param commands The list of parsed commands.
     * @param inputs   The list of user input.
     * @throws DukeException if Find command has missing arguments.
     */
    private static void parseFindCommand(ArrayList<String> commands, ArrayList<String> inputs) throws DukeException {
        String description = getArg("keyword", "Find", inputs, 1, inputs.size() - 1);
        commands.add(description);
    }

    /**
     * Parse a Todo command.
     *
     * @param commands The list of parsed commands.
     * @param inputs   The list of user input.
     * @throws DukeException if Todo command has missing arguments.
     */
    private static void parseTodoCommand(ArrayList<String> commands, ArrayList<String> inputs) throws DukeException {
        String description = getArg("description", "todo", inputs, 1, inputs.size() - 1);
        commands.add(description);
    }

    /**
     * Parse a Deadline command.
     *
     * @param commands The list of parsed commands.
     * @param inputs   The list of user input.
     * @throws DukeException if Deadline command has missing arguments.
     */
    private static void parseDeadlineCommand(ArrayList<String> commands, ArrayList<String> inputs)
            throws DukeException {
        int byArgIndex = getIndexOfParam("/by", "Deadline", inputs);
        String description = getArg("description", "Deadline", inputs, 1, byArgIndex - 1);
        String due = getArg("/by", "Deadline", inputs, byArgIndex + 1, inputs.size() - 1);
        commands.add(description);
        commands.add(due);
    }

    /**
     * Parse an Event command.
     *
     * @param commands The list of parsed commands.
     * @param inputs   The list of user input.
     * @throws DukeException if Event command has missing arguments.
     */
    private static void parseEventCommand(ArrayList<String> commands, ArrayList<String> inputs) throws DukeException {
        int fromArgIndex = getIndexOfParam("/from", "Event", inputs);
        int toArgIndex = getIndexOfParam("/to", "Event", inputs);
        String description = getArg("description", "Event", inputs, 1, fromArgIndex - 1);
        String start = getArg("/from", "Event", inputs, fromArgIndex + 1, toArgIndex - 1);
        String end = getArg("/to", "Event", inputs, toArgIndex + 1, inputs.size() - 1);
        commands.add(description);
        commands.add(start);
        commands.add(end);
    }

    /**
     * Parse an input command that has String parameter(s).
     *
     * @param commands The list of parsed commands.
     * @param inputs   The list of user input.
     * @throws DukeException if command has missing arguments.
     */
    private static void parseStringArgumentsCommand(ArrayList<String> commands, ArrayList<String> inputs)
            throws DukeException {
        String command = inputs.get(COMMAND_ARG);
        switch (command) {
        case "todo":
            parseTodoCommand(commands, inputs);
            break;
        case "deadline":
            parseDeadlineCommand(commands, inputs);
            break;
        case "event":
            parseEventCommand(commands, inputs);
            break;
        case "find":
            parseFindCommand(commands, inputs);
            break;
        }
    }

    /**
     * Parse an input string to resolve it into a command and its argument.
     *
     * @param input The input string to be parsed.
     * @return The command and its argument(s) as an ArrayList of Strings.
     * @throws DukeException if command has missing arguments.
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
            // Fallthrough
        case "find":
            parseStringArgumentsCommand(commands, inputs);
            break;
        }
        return commands;
    }
}