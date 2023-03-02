package duke.Commands;

import duke.Storage;
import dukeException.DukeException;
import tasks.Deadline;
import tasks.Event;

public class EventCommand extends Command {
    private Event event;
    public static final String COMMAND_WORD = "event";
    private String desc;
    private boolean isMark;
    private String start;
    private String end;
    public EventCommand(String desc, boolean isMark, String start, String end) {
        this.desc = desc;
        this.isMark = isMark;
        this.start = start;
        this.end = end;
    }
    public void cmd() throws DukeException {

        Event event = new Event(this.desc, this.isMark, this.start, this.end);
        tasks.add(event);
        addTaskPrint(tasks, event);
        Storage.saveTasks(tasks);
    }
}
