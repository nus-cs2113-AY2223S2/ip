package duke.Commands;

import static duke.Duke.LINE_SPACING;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public void cmd() {
        System.out.println(LINE_SPACING);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(LINE_SPACING);
        isExit = true;
    }

}
