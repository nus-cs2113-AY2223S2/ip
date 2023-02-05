/*
* Defines the structure of commands from user input
* Generally, a user command consists of three components.
* 1. Main Command
* 2. Sub Command
* 3. Payload
* User input can be abstracted into:
* <MainCommand> (<Payload>) (--<Subcommand> (<Subcommand payload>) --(<Subcommand2> (...)) ... )
*/

public enum Command {
    TASK_TODO(0, "todo", new String[]{}),
    TASK_DEADLINE(1, "deadline", new String[]{"by"}),
    TASK_EVENT(2, "event", new String[]{"from", "to"}),
    MARK(0, "mark", new String[]{}),
    UNMARK(0, "unmark", new String[]{}),
    EXIT(0, "exit", new String[]{}),
    LIST(0, "list", new String[]{}),
    UNKNOWN_COMMAND(0, "", new String[]{});

    // Defines how many arguments (subcommands) a Command has
    private int argumentLength;
    private String mainCommand;
    private String[] subcommandNames;

    Command(int argumentLength, String commandName, String[] subcommandNames){
        this.argumentLength = argumentLength;
        this.mainCommand = commandName;
        this.subcommandNames = subcommandNames;
    }

    public int getArgumentLength() {
        return argumentLength;
    }

    public String getMainCommand() {
        return mainCommand;
    }

    public String[] getSubcommandNames() {
        return subcommandNames;
    }
}
