package Arsdorint.command;

public class CommandWrong extends Command {
    public CommandWrong(String message) {
        super(COMMAND_NAME);
        this.top = top;
    }

    public CommandWrong(String top, String bottom) {
        this(top);
        this.bottom = bottom;
    }
    public static final String COMMAND_NAME = "wrong command";
    public String top;
    public String bottom;

    @Override
    public CommandRes execute() {
        return new CommandRes(this.top, null, this.bottom);
    }
}
