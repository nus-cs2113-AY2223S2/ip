package ui;

import exceptions.InvalidSyntaxException;
import exceptions.UnrecognizedInputException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import ui.command.AddTaskCommand;
import ui.command.Command;
import ui.command.DeleteTaskCommand;
import ui.command.ListCommand;
import ui.command.MarkTaskCommand;
import ui.command.NoOpCommand;

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
        } else if (Syntax.MODIFY_TASK_COMMANDS.contains(cmdType)) {
            return handleModifyUserTask(cmdType, splitInput);
        } else if (Syntax.ADD_TASK_COMMANDS.contains(cmdType)) {
            return handleAddUserTask(cmdType, splitInput);
        } else {
            throw new UnrecognizedInputException();
        }

        // Dummy command
        return new NoOpCommand();
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
            // Tasks are 0-indexed, user index is 1-indexed
            int userIndex = Integer.parseInt(splitInput[1]);
            int index = userIndex - 1;

            if (cmd.equals(Syntax.MARK.label)) {
                return new MarkTaskCommand(index, true);
            } else if (cmd.equals(Syntax.UNMARK.label)) {
                return new MarkTaskCommand(index, false);
            } else if (cmd.equals(Syntax.DELETE.label)) {
                return new DeleteTaskCommand(index);
            }
        } catch (NumberFormatException ex) {
            if (cmd.equals(Syntax.MARK.label)) {
                throw new InvalidSyntaxException(Syntax.MARK.expectedSyntax);
            } else if (cmd.equals(Syntax.UNMARK.label)) {
                throw new InvalidSyntaxException(Syntax.UNMARK.expectedSyntax);
            } else if (cmd.equals(Syntax.DELETE.label)) {
                throw new InvalidSyntaxException(Syntax.DELETE.expectedSyntax);
            }
        }

        return new NoOpCommand();
    }

    public boolean isRunning() {
        return isRunning;
    }

}
