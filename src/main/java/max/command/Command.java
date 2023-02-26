package max.command;
/*
 * Defines the structure of commands from user input
 * Generally, a user command consists of three components.
 * 1. Main Command
 * 2. Arguments
 * 3. Payload
 * User input can be abstracted into:
 * <MainCommand> (<Payload>) (--<Argument> (<Argument payload>) --(<Argument> (...)) ... )
 */
public enum Command {
    TASK_TODO(1, "todo", new String[]{}),
    TASK_DEADLINE(2, "deadline", new String[]{"by"}),
    TASK_EVENT(3, "event", new String[]{"from", "to"}),
    MARK(1, "mark", new String[]{}),
    UNMARK(1, "unmark", new String[]{}),
    DELETE(1, "delete", new String[]{}),
    EXIT(1, "exit", new String[]{}),
    LIST(1, "list", new String[]{}),
    DEBUG(1, "debug", new String[]{}),
    UNKNOWN_COMMAND(1, "", new String[]{});


    // Defines how many arguments (subcommands + main command) a Command has
    // The argument length for a command must be minimally 1 (itself) e.g. list, mark
    private int argumentLength;
    private String mainCommand;
    private String[] subcommandNames;
    Command(int argumentLength, String commandName, String[] subcommandNames) {
        this.argumentLength = argumentLength;
        this.mainCommand = commandName;
        this.subcommandNames = subcommandNames;
    }

    /**
     * Get the number of arguments expected for a given command
     * @return number of arguments expected
     */
    public int getArgumentLength() {
        return argumentLength;
    }

    /**
     * Get the string format for an action (main command)
     * e.g. list, delete, mark, deadline
     *
     * @return string value of the main command
     */
    public String getMainCommand() {
        return mainCommand;
    }

    public String[] getSubcommandNames() {
        return subcommandNames;
    }
}