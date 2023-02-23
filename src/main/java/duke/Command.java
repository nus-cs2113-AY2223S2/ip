package duke;

/**
 * Type representing a command after it is parsed by the parser
 */
public class Command {
    private CommandType commandType;
    private String[] additionalParameters;

    Command(CommandType commandType, String[] additionalParameters){
        this.commandType = commandType;
        this.additionalParameters = additionalParameters;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String[] getAdditionalParameters() {
        return additionalParameters;
    }

}
