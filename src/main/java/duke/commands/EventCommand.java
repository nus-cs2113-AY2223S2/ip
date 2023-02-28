package duke.commands;

import duke.tasks.Event;
import duke.outputs.Messages;
import duke.file.TaskList;
import duke.ui.*;

/**
 * Command when adding a new event task to tasklist
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * creates a new event task
     *
     * @param input details of the user command
     */

    public void setEvent(String input) {
        //separates event description and times
        String[] deconstructedDetails = input.split(("event | /from | /to "));
        String description = deconstructedDetails[1];
        String startTime = deconstructedDetails[2];
        String endTime = deconstructedDetails[3];
        this.event = new Event(description, startTime, endTime);
    }

    /**
     * Adds a new event task into the tasklist
     *
     * @param input details of the user command
     * @param tasks tasklist which contains all the tasks
     * @param ui    UI to show task addition message
     */
    @Override
    public void execute(String input, TaskList tasks, UI ui) {
        try {
            setEvent(input);
            tasks.addNewTask(event);
            ui.printTaskAddedMessage(tasks, event);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyEventErrorMessage();
        }
    }
}
