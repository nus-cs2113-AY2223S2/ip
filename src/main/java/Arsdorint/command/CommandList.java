package Arsdorint.command;

import Arsdorint.MessageList;
import Arsdorint.data.TaskList;

public class CommandList extends Command {
    public CommandList() {
        super(COMMAND_NAME);
    }

    public static final String COMMAND_NAME = "list";
    public static final String SYNTAX = "TODO";

    @Override
    public CommandRes execute() {
        return new CommandRes(MessageList.MESSAGE_DIVIDER_LIST, TaskList.list, TaskList.getAllMessage());
    }
}
