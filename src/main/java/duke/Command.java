package duke;

import java.util.ArrayList;

public class Command {
    private final CommandType type;
    private String value = "";
    private final ArrayList<Parameter> parameters;

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

    public CommandType getCommandType() {
        return this.type;
    }

    public String getCommandValue() {
        return this.value;
    }

    public String getParameterValueByType(ParameterType type) throws IllegalCommandException {
        for (Parameter parameter : parameters) {
            if (parameter.getParameterType() == type) {
                return parameter.getParameterValue();
            }
        }
        throw new IllegalCommandException(IllegalCommandExceptionType.MISSING_PARAMETER, type.toString().toLowerCase());
    }
}

enum CommandType {
    LIST, MARK, UNMARK, CREATE_TODO, CREATE_DEADLINE, CREATE_EVENT, DELETE, EXIT
}
