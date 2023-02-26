package duke.commands;


public class CommandResult {


    private final String[] resultMessages;

    public CommandResult(String ...messages) {
        this.resultMessages = messages;
    }


    public String[] getResultMessages() {
        return resultMessages;
    }
}
