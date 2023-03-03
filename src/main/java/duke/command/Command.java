package duke.command;

/**
 * Class representing a command after it is parsed by the parser
 */
public class Command {
    private CommandType commandType;
    private String[] additionalParameters;

    public Command(CommandType commandType, String[] additionalParameters){
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
