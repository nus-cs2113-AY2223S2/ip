import commands.*;
import data.exceptions.SherlockException;

public class Parser {
    public Command parse(String fullCommand) throws SherlockException {
        try {
            String[] inputArray = fullCommand.split("\\s+", 2);

            String command = inputArray[0];
            String arguments = inputArray.length > 1 ? inputArray[1] : "";

            switch (command) {
                case "add":
                    return this.constructAddCommand(arguments);

                case "todo":
                    return this.constructTodoCommand(arguments);

                case "deadline":
                    return this.constructDeadlineCommand(arguments);

                case "event":
                    return this.constructEventCommand(arguments);

                case "list":
                    return this.constructListCommand();

                case "mark":
                case "unmark": {
                    boolean intendedDoneValue = command.equals("mark");
                    return constructMarkCommand(arguments, intendedDoneValue);
                }

                case "delete":
                    return this.constructDeleteCommand(arguments);

                case "bye":
                    return this.constructByeCommand();

                default:
                    return new IncorrectCommand("No such command exists!");
            }

        } catch (Exception e) {
            throw new SherlockException("Command processsing error occurred");
        }
    }

    private Command constructAddCommand(String arguments) {
        if (arguments.isEmpty())
            return new IncorrectCommand("Please provide task name");

        return new AddCommand(arguments);
    }

    private Command constructTodoCommand(String arguments) {
        if (arguments.isEmpty())
            return new IncorrectCommand("Please provide todo name");

        return new TodoCommand(arguments);
    }

    private Command constructDeadlineCommand(String arguments) {
        int byFlagIndex = arguments.indexOf("/by");

        if (byFlagIndex < 0)
            return new IncorrectCommand("Please specify the /by flag");

        String name = arguments.substring(0, byFlagIndex).trim();
        String byArgumentValue = arguments.substring(byFlagIndex).substring("/by".length()).trim();

        if (name.isEmpty())
            return new IncorrectCommand("Please provide name argument");

        if (byArgumentValue.isEmpty())
            return new IncorrectCommand("Please provide /by argument");

        return new DeadlineCommand(name, byArgumentValue);
    }

    private Command constructEventCommand(String arguments) {
        int fromFlagIndex = arguments.indexOf("/from");
        int toFlagIndex = arguments.indexOf("/to");

        if (fromFlagIndex < 0)
            return new IncorrectCommand("Please specify the /from command");

        if (toFlagIndex < 0)
            return new IncorrectCommand("Please specify the /to command");

        String name = arguments.substring(0, fromFlagIndex).trim();

        String fromArgumentValue = arguments.substring(fromFlagIndex, toFlagIndex)
                .substring("/from".length()).trim();

        String toArgumentValue = arguments.substring(toFlagIndex)
                .substring("/to".length()).trim();

        // Arguments Validation
        if (name.isEmpty())
            return new IncorrectCommand("Please provide the name argument");

        if (fromArgumentValue.isEmpty())
            return new IncorrectCommand("Please provide the /from argument");

        if (toArgumentValue.isEmpty())
            return new IncorrectCommand("Please provide the /to argument");

        return new EventCommand(name, fromArgumentValue, toArgumentValue);
    }

    private Command constructListCommand() {
        return new ListCommand();
    }

    private Command constructMarkCommand(String arguments, boolean isDone) {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new MarkCommand(isDone, taskIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Please provide a valid index");
        }
    }

    private Command constructDeleteCommand(String arguments) {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Please provide a valid index");
        }
    }

    private Command constructByeCommand() {
        return new ByeCommand();
    }
}
