package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

import java.util.HashMap;

public class EventTaskCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventTaskCommand(String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }

        HashMap<String, String> args = Parser.parseArguments(command[1],
                new String[]{command[0], Event.DELIMITER_FROM, Event.DELIMITER_TO});
        if (args.get(command[0]).isEmpty()) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }
        if (!args.containsKey(Event.DELIMITER_FROM)
                || !args.containsKey(Event.DELIMITER_TO)
                || args.get(Event.DELIMITER_FROM).isEmpty()
                || args.get(Event.DELIMITER_TO).isEmpty()) {
            throw new DukeException(Errors.MISSING_TIME.MESSAGE);
        }

        description = args.get(command[0]);
        from = args.get(Event.DELIMITER_FROM);
        to = args.get(Event.DELIMITER_TO);
    }

    @Override
    public void run(TaskList taskList) {
        String taskString = taskList.addTask(new Event(description, from, to));
        Ui.printAddTaskMessage(taskString, taskList);
    }
}
