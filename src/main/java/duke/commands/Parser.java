package duke.commands;

import duke.exception.EmptyCommandException;
import duke.exception.EmptyFilterException;
import duke.exception.EmptyIndexException;
import duke.exception.InvalidIndexException;

import duke.tasks.*;
import duke.ui.Ui;

/**
 * Class containing method for parsing the commands.
 */
public class Parser {
    /**
     * Parses the user input into a command that can be executed
     *
     * @param userInput A string that represents the user's input into the terminal.
     * @param taskList The task list that the parsed command will be executed on
     * @return A command that can be executed by calling the execute method.
     */
    public static Command handleUserInputs(String userInput, TaskList taskList){
        String[] cases = userInput.split(" ", 2);
        String command = cases[0];
        Ui.printLine();
        try {
            if (command.equals("todo")) {
                return new CreateTodoCommand(cases);
            } else if (command.equals("deadline")) {
                return new CreateDeadlineCommand(cases);
            } else if (command.equals("event")) {
                return new CreateEventCommand(cases);
            } else if (command.equals("list")) {
                return new PrintListCommand();
            } else if (command.equals("mark")) {
                return new MarkTaskCommand(cases, taskList);
            } else if (command.equals("unmark")) {
                return new UnmarkTaskCommand(cases, taskList);
            } else if (command.equals("delete")) {
                return new DeleteTaskCommand(cases, taskList);
            } else if (command.equals("find")) {
                return new FindTaskCommand(cases);
            } else if (command.equals("bye")) {
                return new ExitCommand();
            } else {
                return new InvalidCommand();
            }
        } catch (EmptyCommandException e) {
            Ui.printEmptyCommandMessage(command);
        } catch (InvalidIndexException e) {
            Ui.printInvalidIndexMessage();
        } catch (EmptyIndexException e) {
            Ui.printEmptyIndexMessage();
        } catch (EmptyFilterException e) {
            Ui.printEmptyFilterMessage();
        }
        return null;
    }

}

