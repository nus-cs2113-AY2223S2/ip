package DukeCommandLine;
public class DukeCommandLineInput {
    String commandType;
    String commandMessage;
    public DukeCommandLineInput(String commandType, String commandMessage) {
        this.commandType = commandType;
        this.commandMessage = commandMessage;
    }
    public String getCommandType() {
        return this.commandType;
    }
    public String getCommandMessage() {
        return this.commandMessage;
    }
}
