package duke.command;

import duke.Parser;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;
import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;

import java.io.IOException;

import static duke.Parser.COMMAND_DEADLINE_WORD;
import static duke.Parser.COMMAND_EVENT_WORD;
import static duke.Parser.COMMAND_TODO_WORD;
import static duke.task.TaskList.allTasks;

public class AddCommand extends Command {

    protected String type;
    protected String desc;
    protected String by;
    protected String from;
    protected String to;

    public AddCommand(String type, String param) throws InvalidDeadline, InvalidEvent {
        this.type = type;
        switch(type){
        case COMMAND_TODO_WORD:
            this.desc = param;
            break;
        case COMMAND_DEADLINE_WORD:
            String[] paramAndBy = Parser.parseDeadline(param);
            this.desc = paramAndBy[0];
            this.by = paramAndBy[1];
            break;
        case COMMAND_EVENT_WORD:
            String[] paramAndFromTo = Parser.parseEvent(param);
            this.desc = paramAndFromTo[0];
            this.from = paramAndFromTo[1];
            this.to = paramAndFromTo[2];
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch(type) {
        case COMMAND_TODO_WORD:
            tasks.addToDo(desc);
            break;
        case COMMAND_DEADLINE_WORD:
            tasks.addDeadline(desc, by);
            break;
        case COMMAND_EVENT_WORD:
            tasks.addEvent(desc, from, to);
            break;
        }
        ui.printAddMessage(allTasks.get(allTasks.size() - 1));
        try {
            Storage.update();
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }


}
