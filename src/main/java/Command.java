public abstract class Command {
    private final String[] commands;

    public Command(String[] commands) {
        this.commands = commands;
    }

    public String[] getCommands() {
        return commands;
    }

    public abstract void doCommand();
}
