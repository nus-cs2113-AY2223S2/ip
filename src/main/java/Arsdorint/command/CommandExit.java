package Arsdorint.command;

public class CommandExit extends Command {
    public CommandExit() {
        super(COMMAND_NAME);
    }

    public static final String COMMAND_NAME = "exit";
    public static final String MESSAGE_TOP = "Bye!";
    public static boolean isExit(Command command) {
        return command.commandType.equalsIgnoreCase("COMMAND_NAME");
    }

    @Override
    public CommandRes execute() {
        return new CommandRes(MESSAGE_TOP);
    }
}

