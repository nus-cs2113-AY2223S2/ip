package dev.joulev.archduke.commands;

import java.util.Arrays;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.ParserException;
import dev.joulev.archduke.exceptions.ParserException.ParserExceptionCode;

/**
 * This class implements a static parser {@link #parse} that can parse the user
 * input in a safe way into a format that the program can programmatically
 * understand.
 */
public class Parser {
    private static Command prepareCommand(String commandType, String body, String from, String to,
            String by) throws ArchdukeException {
        if (commandType != null) {
            commandType = commandType.trim();
        }
        if (body != null) {
            body = body.trim();
        }
        if (from != null) {
            from = from.trim();
        }
        if (to != null) {
            to = to.trim();
        }
        if (by != null) {
            by = by.trim();
        }

        return new Command(commandType, body, from, to, by);
    }

    private static Command getCommandFromComponents(String[] components, String[] options)
            throws ArchdukeException {
        String commandType = components[0];
        String body = components.length > 1 ? components[1] : null;

        String from = null;
        String to = null;
        String by = null;

        for (String rawOption : options) {
            String[] option = rawOption.split(" ", 2);
            String optionName = option[0];
            String optionValue = option.length > 1 ? option[1] : null;

            switch (optionName) {
            case "from":
                from = optionValue;
                break;
            case "to":
                to = optionValue;
                break;
            case "by":
                by = optionValue;
                break;
            default:
                throw new ParserException(ParserExceptionCode.UNKNOWN_OPTION, optionName);
            }
        }

        return prepareCommand(commandType, body, from, to, by);
    }

    /**
     * Safely parses the user input into a {@link Command} object. The format is
     * similar to a Windows command prompt format, for historical reasons. Format:
     * {@code cmd-type cmd body /opt1 option 1 value /opt2 option 2 value}...
     * 
     * @param input The input provided by the user
     * @return A {@link Command} object that contains the parsed information, with
     *         the main command, the body (if any) and the supported options
     *         ({@code /from}, {@code /to} and {@code /by}) (if any).
     * @throws ArchdukeException If the input is invalid, contains unknown option(s)
     *                           or otherwise cannot be parsed.
     */
    public static Command parse(String input) throws ArchdukeException {
        String[] parts = input.split(" /");
        String[] components = parts[0].split(" ", 2);
        String[] options = Arrays.copyOfRange(parts, 1, parts.length);
        return getCommandFromComponents(components, options);
    }
}
