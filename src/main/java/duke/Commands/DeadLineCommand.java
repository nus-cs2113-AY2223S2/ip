package duke.Commands;

import duke.Storage;
import dukeException.DukeException;
import tasks.Deadline;

public class DeadLineCommand extends Command {
    private Deadline deadline;
    public static final String COMMAND_WORD = "deadline";
    private String desc;
    private boolean isMark;
    private String by;

    public DeadLineCommand(String desc, boolean isMark, String by) {
        this.desc = desc;
        this.isMark = isMark;
        this.by = by;
    }
    public void cmd() throws DukeException {

        Deadline dL = new Deadline(this.desc, this.isMark, this.by);
        tasks.add(dL);
        addTaskPrint(tasks, dL);
        Storage.saveTasks(tasks);

    }
}
