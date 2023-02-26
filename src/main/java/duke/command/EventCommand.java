package duke.command;

import duke.task.Event;
import duke.data.TaskList;
import duke.ui.*;
/**
 * Command to execute when adding new event object to task list
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * creates a new event object
     *
     * @param input data of given command and description
     */
    public void setEvent(String input) {
        String[] temp = input.split(("event | /from | /to ")); //separates event description and times
        String description = temp[1];
        String from = temp[2];
        String to = temp[3];
        this.event = new Event(description, from, to);
    }

    /**
     * Adds a new event object into the task list
     *
     * @param input data of given command and description
     * @param tasks task list containing all current tasks
     * @param ui    UI object to print task successfully added statement
     */
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setEvent(input);
            tasks.addTask(event);
            ui.printTaskAddedStatement(tasks, event);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyEventMessage();
        }
    }
}
