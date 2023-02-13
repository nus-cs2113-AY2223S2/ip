package duke.utils;

import duke.exceptions.DukeException;

public class CommandAction {
    /**
     * Retrieve the command from the user's entered input.
     * Invalid command will return an error.
     * 
     * @param input the user inputted text
     * @return command specified by the user
     * @throws DukeException when an invalid command is entered
     */
    public static Command getCommand(String input) throws DukeException {
        try {
            String[] splitInput = input.split(" ", 2);
            Command command = Command.valueOf(splitInput[0].toUpperCase());
            return command;
        } catch (IllegalArgumentException err) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND.toString());
        }
    }

    /**
     * Retrieve the parameters from the user's entered input.
     * If there is no parameters specified, return empty string.
     * 
     * @param input the user inputted text
     * @return parameters specified by the user
     */
    public static String getParameters(String input) {
        if (input.contains(" ")) {
            return input.split(" ", 2)[1];
        }

        return "";
    }

    /**
     * Checks for empty/whitespace parameters input by the user.
     * Throws an IllegalArgumentException if check fails.
     * 
     * @param input the user inputted text
     * @return parameters specified by the user
     * @throws IllegalArgumentException when check fails
     */
    public static void areValidParameters(String[] parameters) throws IllegalArgumentException {
        for (String parameter : parameters) {
            if (parameter.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static int validateItem(String parameters, int itemsSize) throws DukeException {
        try {
            int num = Integer.parseInt(parameters) - 1;
            if (num >= 0 && num < itemsSize) {
                return num;
            } else {
                throw new DukeException(Message.ERROR_MARK_OUT_OF_BOUNDS.toString());
            }
        } catch (NumberFormatException err) {
            throw new DukeException(Message.ERROR_MARK_INVALID_PARAMETER.toString());
        }
    }
}