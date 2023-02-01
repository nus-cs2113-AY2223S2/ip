import java.util.HashMap;

public class ParsedCommand {
    private String command;
    private String positionalArgument;
    private HashMap<String, String> flagArguments;
    public ParsedCommand(String command) {
        String[] commandAndArguments = command.split(" ", 2);
        this.command = commandAndArguments[0];
        String arguments;
        if(commandAndArguments.length == 2) {
            arguments = commandAndArguments[1];
        } else {
            return;
        }

        String[] positionalAndFlagArguments = arguments.split("/");
        this.positionalArgument = positionalAndFlagArguments[0].trim();
        this.flagArguments = new HashMap<>();
        for(int i = 1; i < positionalAndFlagArguments.length; i++) {
            String[] flagAndFlagArgument = positionalAndFlagArguments[i].split(" ", 2);
            if (flagAndFlagArgument.length == 2) {
                this.flagArguments.put(flagAndFlagArgument[0], flagAndFlagArgument[1]);
            } else {
                return;
            }
        }
    }

    public String getCommand() {
        return command;
    }

    public String getPositionalArgument() {
        return positionalArgument;
    }

    public String getFlagArgument(String flag) {
        return this.flagArguments.get(flag);
    }
}
