package Arsdorint.command;

import Arsdorint.data.TaskList;
import Arsdorint.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandFind extends Command {

    public CommandFind(String key) {
        super(COMMAND_NAME);
        this.key = key;
    }
    public static final String COMMAND_NAME = "find";
    public static final String SYNTAX = "Find syntax\n\t>>>find <keyword>";
    public String key;

    @Override
    public CommandRes execute() {
        ArrayList<Task> task = new ArrayList<>(TaskList.list.stream().filter(i -> i.description
                .equals(this.key)).collect(Collectors.toList()));
        int count = (int) task.stream().count();
        String messageTop;
        if (count != 0) {
            messageTop = "Here are the matching tasks in your list, with "
                    + count + " " + TaskList.printTasksOrTask(count) + " as followed:";
        } else {
            messageTop = "There are no tasks matching your search";
        }
        return new CommandRes(messageTop, task, "");
    }
}
