package Arsdorint.command;

import Arsdorint.MessageList;
import Arsdorint.data.TaskList;
import Arsdorint.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandDelete extends Command {

    public CommandDelete(int...idx) {
        super(COMMAND_NAME);
        this.idx = idx;
    }
    public static final String COMMAND_NAME = "delete";
    public static final String SYNTAX = "TODO";
    public static final String MESSAGE_TOP = "TODO";
    public int[] idx;

    @Override
    public CommandRes execute() {
        ArrayList<Task> task = new ArrayList<Task>(IntStream.range(0, TaskList.list.size()).filter
                (i -> contains(idx, i)).mapToObj(i -> TaskList.list.get(i)).collect(Collectors.toList()));
        Arrays.sort(idx);
        for (int i = idx.length - MessageList.OFFSET; i > -MessageList.OFFSET; i--) {
            TaskList.list.remove(idx[1]);
        }
        return new CommandRes(MESSAGE_TOP, task, TaskList.getAllMessage());
    }
}
