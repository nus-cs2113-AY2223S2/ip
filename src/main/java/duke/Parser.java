package duke;

import java.util.ArrayList;

public class Parser {
    public String[] parseCommand(String userInputCommand) {
        final String[] split = userInputCommand.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[]{split[0], ""};
        return commandTypeAndParams;
    }

}
