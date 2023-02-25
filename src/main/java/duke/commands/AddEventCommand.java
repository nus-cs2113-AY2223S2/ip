package duke.commands;

import java.time.LocalDateTime;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class AddEventCommand implements Command {
    private String arguments;
    public static final String COMMAND_WORD = "event";
    private static final int INDEX_DESCRIPTION_PARSED = 0;
    private static final int INDEX_STARTTIME_PARSED = 1;
    private static final int INDEX_ENDTIME_PARSED = 2;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        if (!arguments.isBlank()) {
            Parser parser = new Parser();
            String description = null;
            LocalDateTime startTime = null;
            LocalDateTime endTime = null;
            String[] parsedString = parser.parseEvent(arguments);

            if (parsedString != null) {
                description = parsedString[INDEX_DESCRIPTION_PARSED];
                startTime = parser.parseDateTime(parsedString[INDEX_STARTTIME_PARSED]);
                endTime = parser.parseDateTime(parsedString[INDEX_ENDTIME_PARSED]);
            }

            if (description != null & startTime != null & endTime != null) {
                Task newTask = new Event(description, startTime, endTime);
                tasks.addTask(newTask);
                ui.showNewTaskMessage(tasks, newTask);
            }
        } else {
            ui.showPromptEmptyErrorMessage(COMMAND_WORD);
        }
    }

    public boolean isExit() {
        return false;
    }
}
