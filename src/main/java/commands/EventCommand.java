package commands;

import storage.Storage;
import task.Event;
import task.TaskParser;
import ui.TextUi;

/**
 * Handles the event command.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String description;
    private String time;

    /**
     * A constructor that accepts the description and the /to and /from time specified by the user.
     *
     * @param description The description of the deadline.
     * @param time        the /to and /from of the event.
     */
    public EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Executes the command the user provided.
     *
     * @param taskParser The task parser that Duke use to determine what task to execute.
     * @param ui         The Ui instance. Use to display messages to users.
     * @param storage    The storage instance. Use to write data into the text file.
     */
    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        Event event = taskParser.createEventTask(description, time);
        taskParser.addAndPrintTask(event, ui, storage);
    }

    /**
     * Check if the program is exiting.
     *
     * @return the exit value.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
