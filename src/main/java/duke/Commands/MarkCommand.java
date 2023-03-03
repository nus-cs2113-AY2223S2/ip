package duke.Commands;

import duke.Storage;

import static duke.Duke.LINE_SPACING;

public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    private int idx;
    public MarkCommand(int idx) {
        this.idx = idx;
    }
    public void cmd() {
        tasks.getTask(this.idx).mark();
        System.out.println("\tNice! I'velis marked this task as done:");
        System.out.println("\t  " + tasks.getTask(this.idx));
        Storage.saveTasks(tasks);
    }

}
