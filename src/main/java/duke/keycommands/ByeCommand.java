package duke.keycommands;

import duke.common.Common;

public class ByeCommand {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
        doByeCommand();
    }

    public void doByeCommand() {
        System.out.println(BYE_MESSAGE);
        System.out.println(Common.HORIZONTAL_LINE);
        System.exit(0);
    }

}
