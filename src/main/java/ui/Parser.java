package ui;

import exceptions.InvalidSyntaxException;
import exceptions.UnrecognizedInputException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import ui.command.AddTaskCommand;
import ui.command.Command;
import ui.command.DeleteTaskCommand;
import ui.command.FindCommand;
import ui.command.ListCommand;
import ui.command.MarkTaskCommand;

public class Parser {

    private boolean isRunning;

    public Parser() {
        isRunning = true;
    }

    public Command parse(String userInput)
            throws UnrecognizedInputException, InvalidSyntaxException {
        String[] splitInput = userInput.split("\\s+", 2);
        // Case-insensitive to user input
        String cmdType = splitInput[0].trim().toLowerCase();

        if (cmdType.equals(Syntax.EXIT.label)) {
            isRunning = false;
        } else if (cmdType.equals(Syntax.LIST.label)) {
            return new ListCommand();
        } else if (cmdType.equals(Syntax.FIND.label)) {
            if (splitInput.length < 2) {
                throw new InvalidSyntaxException(Syntax.FIND.expectedSyntax);
            }

            String keyword = splitInput[1].trim();
            return new FindCommand(keyword);
        } else if (Syntax.MODIFY_TASK_COMMANDS.contains(cmdType)) {
            return handleModifyUserTask(cmdType, splitInput);
        } else if (Syntax.ADD_TASK_COMMANDS.contains(cmdType)) {
            return handleAddUserTask(cmdType, splitInput);
        }

        throw new UnrecognizedInputException();
    }

    private Command handleAddUserTask(String cmd, String[] splitInput) throws InvalidSyntaxException {
        Task task = null;
        if (cmd.equals(Syntax.TODO.label)) {
            task = Task.createFromInput(splitInput);
        } else if (cmd.equals(Syntax.DEADLINE.label)) {
            task = DeadlineTask.createFromInput(splitInput);
        } else if (cmd.equals(Syntax.EVENT.label)) {
            task = EventTask.createFromInput(splitInput);
        }

        return new AddTaskCommand(task);
    }

    private Command handleModifyUserTask(String cmd, String[] splitInput)
            throws InvalidSyntaxException, IndexOutOfBoundsException {
        try {
            Syntax matchedSyntax;
            if (cmd.equals(Syntax.MARK.label)) {
                matchedSyntax = Syntax.MARK;
            } else if (cmd.equals(Syntax.UNMARK.label)) {
                matchedSyntax = Syntax.UNMARK;
            } else {
                matchedSyntax = Syntax.DELETE;
            }

            // Check for valid length of command
            if (splitInput.length < 2) {
                throw new InvalidSyntaxException(matchedSyntax.expectedSyntax);
            }

            // Tasks are 0-indexed, user index is 1-indexed
            int userIndex = Integer.parseInt(splitInput[1]);
            int index = userIndex - 1;

            if (cmd.equals(Syntax.MARK.label)) {
                return new MarkTaskCommand(index, true);
            } else if (cmd.equals(Syntax.UNMARK.label)) {
                return new MarkTaskCommand(index, false);
            } else {
                return new DeleteTaskCommand(index);
            }

        } catch (NumberFormatException ex) {
            if (cmd.equals(Syntax.MARK.label)) {
                throw new InvalidSyntaxException(Syntax.MARK.expectedSyntax);
            } else if (cmd.equals(Syntax.UNMARK.label)) {
                throw new InvalidSyntaxException(Syntax.UNMARK.expectedSyntax);
            } else {
                throw new InvalidSyntaxException(Syntax.DELETE.expectedSyntax);
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

}
