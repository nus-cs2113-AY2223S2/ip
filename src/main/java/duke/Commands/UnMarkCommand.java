package duke.Commands;

import duke.Storage;

import static duke.Duke.LINE_SPACING;

public class UnMarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int idx;
    public UnMarkCommand(int idx) {
        this.idx = idx;
    }
    public void cmd() {
        tasks.getTask(this.idx).unMark();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.getTask(this.idx));
        Storage.saveTasks(tasks);
    }
}
