package Arsdorint.command;

import Arsdorint.data.TaskList;
import Arsdorint.task.Deadline;
import Arsdorint.task.Task;

import java.util.ArrayList;

public class CommandDeadline extends Command {
    public CommandDeadline(String description, String date) {
        super(COMMAND_NAME);
        this.description = description;
        this.date = date;
    }

    public static final String COMMAND_NAME = "deadline";
    public static final String SYNTAX = "TODO";
    public static final String MESSAGE_TOP = "TODO";
    public String description;
    public String date;
    public ArrayList<Task> task = new ArrayList<Task>();

    @Override
    public CommandRes execute() {
        Task added = new Deadline(description, date);
        TaskList.list.add(added);
        task.add(added);
        return new CommandRes(MESSAGE_TOP, task, TaskList.getAllMessage());
    }
}
