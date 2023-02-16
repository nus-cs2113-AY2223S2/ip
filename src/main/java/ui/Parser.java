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
import ui.command.NoOpCommand;

public class Parser {

    private boolean isRunning;

    public Parser() {
        isRunning = true;
    }

    /**
     * Parses user input into the respective {@link Command}
     *
     * @param userInput Raw user input
     * @return Respective command
     * @throws UnrecognizedInputException If user input has unrecognized command root
     * @throws InvalidSyntaxException     If user input does not match expected syntax
     */
    public Command parse(String userInput)
            throws UnrecognizedInputException, InvalidSyntaxException {
        String[] splitInput = userInput.split("\\s+", 2);
        // Case-insensitive to user input
        String cmdType = splitInput[0].trim().toLowerCase();

        if (cmdType.equals(Syntax.EXIT.root)) {
            isRunning = false;
            return new NoOpCommand();
        } else if (cmdType.equals(Syntax.LIST.root)) {
            return new ListCommand();
        } else if (cmdType.equals(Syntax.FIND.root)) {
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

    /**
     * Handles commands relating to adding tasks
     *
     * @param cmd        Root of command
     * @param splitInput Tokenized user input (excluding root)
     * @return Respective command
     * @throws InvalidSyntaxException If user input does not match expected syntax
     * @see Syntax#ADD_TASK_COMMANDS
     */
    private Command handleAddUserTask(String cmd, String[] splitInput) throws InvalidSyntaxException {
        Task task = null;
        if (cmd.equals(Syntax.TODO.root)) {
            task = Task.createFromInput(splitInput);
        } else if (cmd.equals(Syntax.DEADLINE.root)) {
            task = DeadlineTask.createFromInput(splitInput);
        } else if (cmd.equals(Syntax.EVENT.root)) {
            task = EventTask.createFromInput(splitInput);
        }

        return new AddTaskCommand(task);
    }

    /**
     * Handles commands relating to modifying existing tasks
     *
     * @param cmd        Root of command
     * @param splitInput Tokenized user input (excluding root)
     * @return Respective command
     * @throws InvalidSyntaxException    If user input does not match expected syntax
     * @throws IndexOutOfBoundsException If task ID does not refer to an existing task
     * @see Syntax#MODIFY_TASK_COMMANDS
     */
    private Command handleModifyUserTask(String cmd, String[] splitInput)
            throws InvalidSyntaxException, IndexOutOfBoundsException {
        try {
            Syntax matchedSyntax;
            if (cmd.equals(Syntax.MARK.root)) {
                matchedSyntax = Syntax.MARK;
            } else if (cmd.equals(Syntax.UNMARK.root)) {
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

            if (cmd.equals(Syntax.MARK.root)) {
                return new MarkTaskCommand(index, true);
            } else if (cmd.equals(Syntax.UNMARK.root)) {
                return new MarkTaskCommand(index, false);
            } else {
                return new DeleteTaskCommand(index);
            }

        } catch (NumberFormatException ex) {
            if (cmd.equals(Syntax.MARK.root)) {
                throw new InvalidSyntaxException(Syntax.MARK.expectedSyntax);
            } else if (cmd.equals(Syntax.UNMARK.root)) {
                throw new InvalidSyntaxException(Syntax.UNMARK.expectedSyntax);
            } else {
                throw new InvalidSyntaxException(Syntax.DELETE.expectedSyntax);
            }
        }
    }

    /**
     * Whether parser is running; program lifecycle is tied to this
     *
     * @return If parser is running
     */
    public boolean isRunning() {
        return isRunning;
    }

}
