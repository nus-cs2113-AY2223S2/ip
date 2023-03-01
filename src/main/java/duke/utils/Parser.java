package duke.utils;

import duke.model.Command;
import duke.model.Payload;

/**
 * Class that serves as a parser to tokenize the input
 */
public class Parser {

    /**
     * Method to parse the input into command object
     *
     * @param input The string input from the user
     * @return The corresponding command object that is tokenized
     */
    public static Command parse(String input) {
        String trimmedInput = input.trim();
        Command command = new Command();
        String[] commandArray = trimmedInput.split(" ");
        command.setType(commandArray[0].trim());
        String[] payloadStringArray = trimmedInput.split(command.getType());

        if (payloadStringArray.length > 1) {
            command.setPayload(new Payload(trimStringArray(payloadStringArray[1].split("/"))));
        } else {
            command.setPayload(new Payload());
        }
        return command;
    }

    /**
     * Method to trim every String inside an array
     *
     * @param stringArray The string array about to be trimmed
     * @return The trimmed stringArray
     */
    public static String[] trimStringArray(String[] stringArray) {
        String[] trimmedStringArray = new String[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            trimmedStringArray[i] = stringArray[i].trim();
        }
        return trimmedStringArray;
    }


}
