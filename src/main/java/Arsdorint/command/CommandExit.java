package Arsdorint.command;

public class CommandExit extends Command {
    public CommandExit() {
        super(COMMAND_NAME);
    }

    public static final String COMMAND_NAME = "exit";
    public static final String MESSAGE_TOP = " Bye. Hope to see you again soon!\n";
    public static boolean isExit(Command command) {
        return command.commandType.equalsIgnoreCase("COMMAND_NAME");
    }

    /**
     * Execution of the exit command
     *
     * @return printing the exit of command
     *
     */
    @Override
    public CommandRes execute() {
        return new CommandRes(MESSAGE_TOP);
    }
}

