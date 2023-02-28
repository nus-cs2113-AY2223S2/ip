package duke.utils;

import duke.model.Command;
import duke.model.Payload;

public class Parser {

    public static Command parse(String input) {
        Command command = new Command();
        String[] commandArray = input.split(" ");
        command.setType(commandArray[0].trim());
        String[] payloadStringArray = input.split(command.getType());

        if (payloadStringArray.length > 1) {
            command.setPayload(new Payload(trimStringArray(payloadStringArray[1].split("/"))));
        } else {
            command.setPayload(new Payload());
        }
        return command;
    }

    public static String[] trimStringArray(String[] stringArray) {
        String[] trimmedStringArray = new String[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            trimmedStringArray[i] = stringArray[i].trim();
        }
        return trimmedStringArray;
    }

   
}
