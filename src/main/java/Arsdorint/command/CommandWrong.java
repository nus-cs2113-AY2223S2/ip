package Arsdorint.command;

import static Arsdorint.MessageList.MESSAGE_DIVIDER;

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
    public String top = "=( OOPS!!! I'm sorry, but I don't know what that means :-(\n" + MESSAGE_DIVIDER;
    public String bottom = "Error: Lack / Wrong description format of the command";

    @Override
    public CommandRes execute() {
        return new CommandRes(this.top, null, this.bottom);
    }
}
