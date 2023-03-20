package Arsdorint.command;

import Arsdorint.MessageList;
import Arsdorint.data.TaskList;
import Arsdorint.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandMark extends Command {
    public CommandMark(int...idx) {
        super(COMMAND_NAME);
        this.idx = idx;
    }

    public static final String COMMAND_NAME = "mark";
    public static final String SYNTAX = "Syntax for mark\n\t" +
            ">>> mark <item index number> \n" + "Note: item index must exist in the current list";
    public static final String MESSAGE_TOP = "\nGot it. I've marked this task as done:\n" + "\t";
    public int[] idx;

    /**
     * Execution of the "mark" command
     *
     * @return printing the task to be marked with an "X" in its box
     *
     */
    @Override
    public CommandRes execute() {
        for (int i = 0; i < idx.length; i++) {
            TaskList.list.get(idx[i]).setIsDone(true);
        }
        ArrayList<Task> task = new ArrayList<Task>(IntStream.range(0, TaskList.list.size()).filter
                (i -> contains(idx, i)).mapToObj(i -> TaskList.list.get(i)).collect(Collectors.toList()));

        return new CommandRes(MESSAGE_TOP, task, TaskList.getUnmarkedMessage());
    }
}

