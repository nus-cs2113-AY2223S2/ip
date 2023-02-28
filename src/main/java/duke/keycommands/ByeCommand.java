package duke.keycommands;

import duke.common.Common;

/**
 * Represents the command to exit the program.
 */
public class ByeCommand {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Constructs a new ByeCommand object and exits the program.
     */
    public ByeCommand() {
        doByeCommand();
    }

    private void doByeCommand() {
        System.out.println(BYE_MESSAGE);
        System.out.println(Common.HORIZONTAL_LINE);
        System.exit(0);
    }

}
