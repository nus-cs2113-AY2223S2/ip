package tusky.parser;

import tusky.commands.*;
import tusky.constants.Messages;
import tusky.exceptions.EmptyDescriptionException;
import tusky.exceptions.KeyNotFoundException;
import tusky.tasks.*;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    /**
     * Parses the user input and returns a Command object
     *
     * @param input String input from the user containing the command and its parameters
     * @return Command object to be executed
     * @throws EmptyDescriptionException If a command to create a command is given but there is no description
     * @throws IllegalArgumentException  If the provided command is not in CommandType
     * @throws KeyNotFoundException      If a named input like 'from' is not provided
     * @throws NumberFormatException     If the index provided for commands like 'mark' is not a number
     */
    public static Command parse (String input)
            throws NumberFormatException, EmptyDescriptionException, IllegalArgumentException, KeyNotFoundException {
        Map<String, String> valueMap = new HashMap<>();
        String body = "";
        CommandType command;
        String[] inputs = input.split(" ", 2);

        // trim whitespace and convert to uppercase so input matches the enum names
        command = CommandType.valueOf(inputs[0].trim().toUpperCase());

        if (inputs.length > 1) {
            String[] args = inputs[1].split(" /");
            body = args[0].trim();
            for (int i = 1; i < args.length; i++) {
                String[] words = args[i].split(" ", 2);
                valueMap.put(words[0].trim(), words[1].trim());
            }
        }
        int index;
        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            index = Integer.parseInt(body) - 1;
            return new MarkCommand(index);

        case UNMARK:
            index = Integer.parseInt(body) - 1;
            return new UnmarkCommand(index);
        case TODO:
            try {
                return new AddCommand(new ToDo("false", body));
            } catch (EmptyDescriptionException e) {
                throw new EmptyDescriptionException(
                        String.format(Messages.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.TODO)
                );
            }

        case EVENT:
            if (!valueMap.containsKey("from")) {
                throw new KeyNotFoundException("from");
            }
            if (!valueMap.containsKey("to")) {
                throw new KeyNotFoundException("to");
            }
            try {
                return new AddCommand(new Event("false", body, valueMap.get("from"), valueMap.get("to")));
            } catch (EmptyDescriptionException e) {
                throw new EmptyDescriptionException(
                        String.format(Messages.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.EVENT)
                );
            }


        case DEADLINE:
            if (!valueMap.containsKey("by")) {
                throw new KeyNotFoundException("by");
            }
            try {
                return new AddCommand(new Deadline("false", body, valueMap.get("by")));
            } catch (EmptyDescriptionException e) {
                throw new EmptyDescriptionException(
                        String.format(Messages.ERR_EMPTY_TASK_DESCRIPTION.toString(), TaskType.DEADLINE)
                );
            }

        case DELETE:
            index = Integer.parseInt(body) - 1;
            return new DeleteCommand(index);
        case FIND:
            return new FindCommand(body);
        default:
            throw new IllegalArgumentException(Messages.ERR_UNKNOWN_COMMAND.toString());
        }

    }
}
