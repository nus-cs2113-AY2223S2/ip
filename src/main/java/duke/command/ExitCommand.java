package duke.command;

import duke.data.TaskData;

public class ExitCommand extends Command {
    @Override
    public void executeCommand(TaskData taskdata) {
        exitProgram();

    }

    /**
     * Prints out the exit message to the user
     */
    public static void exitProgram() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        System.exit(0);
    }
}
