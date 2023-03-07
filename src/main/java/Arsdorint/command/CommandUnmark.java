package Arsdorint.command;

import Arsdorint.data.TaskList;
import Arsdorint.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandUnmark extends Command {
    public CommandUnmark(int...idx) {
        super(COMMAND_NAME);
        this.idx = idx;
    }

    public static final String COMMAND_NAME = "unmark";
    public static final String SYNTAX = "Syntax for unmark\n\t" +
            ">>> unmark <item index number> \n" + "Note: item index must exist in the current list";
    public static final String MESSAGE_TOP = "\nGot it. I've marked this task as not done yet:\n" + "\t";
    public int[] idx;

    @Override
    public CommandRes execute() {
        for (int i = 0; i < idx.length; i++) {
            TaskList.list.get(idx[i]).setIsDone(false);
        }
        ArrayList<Task> task = new ArrayList<Task>(IntStream.range(0, TaskList.list.size()).filter
                (i -> contains(idx, i)).mapToObj(i -> TaskList.list.get(i)).collect(Collectors.toList()));

        return new CommandRes(MESSAGE_TOP, task, TaskList.getMarkedMessage());
    }
}


