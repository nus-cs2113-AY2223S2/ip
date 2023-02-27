package commands;

import storage.Storage;
import task.Event;
import task.TaskParser;
import ui.TextUi;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String description;
    private String time;

    public EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        Event event = taskParser.createEventTask(description, time);
        taskParser.addAndPrintTask(event, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
