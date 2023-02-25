package duke.commands;

import java.time.LocalDateTime;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class AddDeadlineCommand implements Command {
    private String arguments;
    private static int INDEX_DESCRIPTION_PARSED = 0;
    private static int INDEX_ENDTIME_PARSED = 1;
    public static final String COMMAND_WORD = "deadline";

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        if (!arguments.isBlank()) {
            Parser parser = new Parser();
            String description = null;
            LocalDateTime endTime = null;
            String[] parsedString = parser.parseDeadline(arguments);

            if (parsedString != null) {
                description = parsedString[INDEX_DESCRIPTION_PARSED];
                endTime = parser.parseDateTime(parsedString[INDEX_ENDTIME_PARSED]);
            }

            if (description != null & endTime != null) {
                Task newTask = new Deadline(description, endTime);
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
