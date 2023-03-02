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
        System.out.println(LINE_SPACING);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.getTask(this.idx));
        System.out.println(LINE_SPACING);
        Storage.saveTasks(tasks);
    }

}
