package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

import java.util.HashMap;

/**
 * Command for adding an event task to the task list.
 */
public class EventTaskCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a command that will add an event task to the task list.
     *
     * @param args Array that should contain the task description and start and end times at index 1.
     * @throws DukeException If no description, start time, or end time is provided for the event task.
     */
    public EventTaskCommand(String[] args) throws DukeException {
        if (args.length < 2) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }

        HashMap<String, String> parsedArgs = Parser.parseArguments(args[1],
                new String[]{args[0], Event.DELIMITER_FROM, Event.DELIMITER_TO});
        if (parsedArgs.get(args[0]).isEmpty()) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }
        if (!parsedArgs.containsKey(Event.DELIMITER_FROM)
                || !parsedArgs.containsKey(Event.DELIMITER_TO)
                || parsedArgs.get(Event.DELIMITER_FROM).isEmpty()
                || parsedArgs.get(Event.DELIMITER_TO).isEmpty()) {
            throw new DukeException(Errors.MISSING_TIME.MESSAGE);
        }

        description = parsedArgs.get(args[0]);
        from = parsedArgs.get(Event.DELIMITER_FROM);
        to = parsedArgs.get(Event.DELIMITER_TO);
    }

    /**
     * Adds an event task with the description and deadline provided in the constructor to the task list.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void run(TaskList taskList) {
        String taskString = taskList.addTask(new Event(description, from, to));
        Ui.printAddTaskMessage(taskString, taskList);
    }
}
