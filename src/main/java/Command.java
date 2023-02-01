public class Command {
    private CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public String[] getAdditionalParameters() {
        return additionalParameters;
    }

    private String[] additionalParameters;
    Command(CommandType commandType, String[] additionalParameters){
        this.commandType = commandType;
        this.additionalParameters = additionalParameters;
    }
}
