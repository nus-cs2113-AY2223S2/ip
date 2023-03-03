package duke;

import java.util.ArrayList;

/**
 * Class representing a command and its parameters. Must be created using a command string.
 */
public class Command {
    private final CommandType type;
    private String value = "";
    private final ArrayList<Parameter> parameters;

    /**
     * Constructor for a command.
     * @param input the full string of the entered command, including parameters
     * @throws IllegalCommandException command is invalid
     * @throws IllegalParameterException command is valid but parameter requirements are not satisfied
     */
    public Command(String input) throws IllegalCommandException, IllegalParameterException {
        String[] splitInput = input.split(" /");

        // handle command
        String commandString = splitInput[0].split(" ", 2)[0];

        switch (commandString) {
            case "list":
                this.type = CommandType.LIST;
                break;
            case "mark":
                this.type = CommandType.MARK;
                break;
            case "unmark":
                this.type = CommandType.UNMARK;
                break;
            case "todo":
                this.type = CommandType.CREATE_TODO;
                break;
            case "deadline":
                this.type = CommandType.CREATE_DEADLINE;
                break;
            case "event":
                this.type = CommandType.CREATE_EVENT;
                break;
            case "delete":
                this.type = CommandType.DELETE;
                break;
            case "bye":
                this.type = CommandType.EXIT;
                break;
            default:
                throw new IllegalCommandException(IllegalCommandExceptionType.COMMAND_DOES_NOT_EXIST, commandString);
        }

        if (
                this.type == CommandType.CREATE_TODO ||
                this.type == CommandType.CREATE_DEADLINE ||
                this.type == CommandType.CREATE_EVENT ||
                this.type == CommandType.DELETE ||
                this.type == CommandType.MARK ||
                this.type == CommandType.UNMARK
        ) {
            try {
                this.value = splitInput[0].split(" ", 2)[1];
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalCommandException(IllegalCommandExceptionType.MISSING_VALUE, commandString);
            }
        }

        // handle additional parameters
        this.parameters = new ArrayList<>();
        for (int i = 1; i < splitInput.length; i++) {
            Parameter parameter = new Parameter(splitInput[i]);
            parameters.add(parameter);
        }

        // handle missing parameters based on command type
        if (this.type == CommandType.CREATE_DEADLINE) {
            this.getParameterValueByType(ParameterType.DEADLINE);
        } else if (this.type == CommandType.CREATE_EVENT) {
            this.getParameterValueByType(ParameterType.EVENT_START);
            this.getParameterValueByType(ParameterType.EVENT_END);
        }
    }

    /**
     * Gets the string value of the specified parameter type.
     * @param type the type of the parameter to query
     * @return the string value of the queried parameter type
     * @throws IllegalCommandException the command is missing the queried parameter type
     */
    public String getParameterValueByType(ParameterType type) throws IllegalCommandException {
        for (Parameter parameter : parameters) {
            if (parameter.getParameterType() == type) {
                return parameter.getParameterValue();
            }
        }
        throw new IllegalCommandException(IllegalCommandExceptionType.MISSING_PARAMETER, type.toString().toLowerCase());
    }

    /**
     * Executes the command.
     * @param tasks the list of tasks
     * @param ui the ui object
     * @param storage the storage object
     * @throws Exception some kind of error occurred
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (type == CommandType.CREATE_TODO) {
            Task addedTask = tasks.addTask(new Todo(value));
            storage.save(tasks);
            ui.showAddedTask(tasks, addedTask);
        } else if (type == CommandType.CREATE_DEADLINE) {
            String description = value;
            String deadline = getParameterValueByType(ParameterType.DEADLINE);
            Task addedTask = tasks.addTask(new Deadline(description, deadline));
            storage.save(tasks);
            ui.showAddedTask(tasks, addedTask);
        } else if (type == CommandType.CREATE_EVENT) {
            String description = value;
            String from = getParameterValueByType(ParameterType.EVENT_START);
            String to = getParameterValueByType(ParameterType.EVENT_END);
            Task addedTask = tasks.addTask(new Event(description, from, to));
            ui.showAddedTask(tasks, addedTask);
        } else if (type == CommandType.DELETE) {
            int displayedIndex = Integer.parseInt(value);
            Task removedTask = tasks.removeTaskByDisplayedIndex(displayedIndex);
            storage.save(tasks);
            ui.showRemovedTask(removedTask);
        } else if (type == CommandType.LIST) {
            ui.showAllTasks(tasks);
        } else if (type == CommandType.MARK) {
            int displayedIndex = Integer.parseInt(value);
            Task markedTask = tasks.markTaskByDisplayedIndex(displayedIndex);
            storage.save(tasks);
            ui.showMarkedTask(markedTask);
        } else if (type == CommandType.UNMARK) {
            int displayedIndex = Integer.parseInt(value);
            Task unmarkedTask = tasks.unmarkTaskByDisplayedIndex(displayedIndex);
            storage.save(tasks);
            ui.showUnmarkedTask(unmarkedTask);
        }
    }

    /**
     * Check if the command is the exit command.
     * @return boolean indicating if the program should exit
     */
    public boolean isExit() {
        return type == CommandType.EXIT;
    }
}

enum CommandType {
    LIST, MARK, UNMARK, CREATE_TODO, CREATE_DEADLINE, CREATE_EVENT, DELETE, EXIT
}
