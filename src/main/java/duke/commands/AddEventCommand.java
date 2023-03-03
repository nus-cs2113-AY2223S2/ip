package duke.commands;

import java.time.LocalDateTime;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * The AddEventCommand class represents a command to add a deadline to the task list.
 */
public class AddEventCommand implements Command {
    private String arguments;
    public static final String COMMAND_WORD = "event";
    private static final int INDEX_DESCRIPTION_PARSED = 0;
    private static final int INDEX_STARTTIME_PARSED = 1;
    private static final int INDEX_ENDTIME_PARSED = 2;

    /**
     * Constructor for the AddEventCommand class.
     *
     * @param arguments The arguments to be parsed to create the new Deadline task.
     */
    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the AddDeadlineCommand by parsing the arguments to create a new Event task and adding it to the TaskList.
     * If the arguments are invalid or incomplete, an error message is displayed.
     *
     * @param tasks   The TaskList object containing the list of tasks.
     * @param ui      The TextUi object to handle user input and output.
     * @param storage The Storage object to handle reading and writing of task data to a file.
     */
    @Override
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

    /**
     * Returns a boolean value indicating if the command is an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
