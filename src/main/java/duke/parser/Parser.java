package duke.parser;

import duke.outputs.Messages;
import duke.commands.*;
import duke.file.TaskList;
import duke.ui.UI;

/**
 * Parser that handles the user inputs
 */
public class Parser {
    private final String input;
    public boolean isByeCommand = false;

    public Parser(String input) {
        this.input = input;
    }

    /**
     * Filter out the command word from the user input
     *
     * @return the command word and the corresponding command to run
     */
    private String getCommandWord() {
        String[] deconstructedDetails = input.split(" ", 2);
        return deconstructedDetails[0];
    }

    /**
     * Handles all types of commands based on the command word filtered
     *
     * @param tasks tasklist which contains all tasks
     * @param ui    UI to print exit message when user terminates program
     */
    public void runCommand(TaskList tasks, UI ui) {
        String commandWord = getCommandWord();
        Command command;
        switch (commandWord) {

        case "todo":
            command = new TodoCommand();
            command.execute(input, tasks, ui);
            break;

        case "deadline":
            command = new DeadlineCommand();
            command.execute(input, tasks, ui);
            break;

        case "event":
            command = new EventCommand();
            command.execute(input, tasks, ui);
            break;

        case "list":
            command = new ListCommand();
            command.execute(input, tasks, ui);
            break;

        case "bye":
            UI.endProgram();
            isByeCommand = true;
            break;

        case "mark":
            command = new MarkCommand();
            command.execute(input, tasks, ui);
            break;

        case "unmark":
            command = new UnmarkCommand();
            command.execute(input, tasks, ui);
            break;

        case "delete":
            command = new DeleteCommand();
            command.execute(input, tasks, ui);
            break;

        case "find":
            command = new FindCommand();
            command.execute(input, tasks, ui);
            break;
        default:
            Messages.unknownCommandErrorMessage();
        }
    }

}
