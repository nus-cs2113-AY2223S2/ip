package duke.Commands;

/*
 * ThRepresents the Exit Command that is used to terminate the program
 *
 */

import static duke.Duke.LINE_SPACING;

public class ExitCommand extends Command {
    /**
     * The keyword associated with the ExitCommand.
     */
    public static final String COMMAND_WORD = "exit";

    /**
     * Executes the ExitCommand by printing a farewell message and setting the isExit flag to true.
     */
    public void cmd() {
        System.out.println(LINE_SPACING);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(LINE_SPACING);
        isExit = true;
    }

}
