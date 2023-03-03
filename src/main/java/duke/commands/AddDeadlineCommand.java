package duke.commands;

import java.time.LocalDateTime;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * The AddDeadlineCommand class represents a command to add an event to the task list.
 */
public class AddDeadlineCommand implements Command {
    private String arguments;
    private static int INDEX_DESCRIPTION_PARSED = 0;
    private static int INDEX_ENDTIME_PARSED = 1;
    public static final String COMMAND_WORD = "deadline";

    /**
     * Constructor for the AddDeadlineCommand class.
     *
     * @param arguments The arguments to be parsed to create the new Deadline task.
     */

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the AddDeadlineCommand by parsing the arguments to create a new Deadline task and adding it to the TaskList.
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
