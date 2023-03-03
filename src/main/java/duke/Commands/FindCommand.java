package duke.Commands;

import duke.Ui;

/*
 * Represents the find command that extends the Command class*/
public class FindCommand extends Command {
    /**
     * The keyword associated with the FindCommand.
     */
    public static final String COMMAND_WORD = "find";

    /**
     * The keyword to search for.
     */
    private final String term;

    /**
     * Constructs a FindCommand object with the specified keyword to search for.
     *
     * @param term The keyword to search for.
     */
    public FindCommand(String term) {
        this.term = term;
    }

    /**
     * Executes the FindCommand by searching for tasks in the task list
     * that contain the specified keyword and printing them out.
     */
    public void cmd() {
        Ui ui = new Ui();
        ui.showLine();
        if (tasks.getSize() == 0) {
            System.out.println("There are no tasks to search.");
        } else {
            System.out.println("\t Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).getDescription().contains(this.term)) {
                    System.out.println(tasks.getTask(i));
                }
            }
        }

    }

}
