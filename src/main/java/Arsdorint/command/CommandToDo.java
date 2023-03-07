package Arsdorint.command;

import Arsdorint.data.TaskList;
import Arsdorint.task.Deadline;
import Arsdorint.task.Task;
import Arsdorint.task.Todo;

import java.util.ArrayList;

public class CommandToDo extends Command {
    public CommandToDo(String description) {
        super(COMMAND_NAME);
        this.description = description;
    }

    public static final String COMMAND_NAME = "todo";
    public static final String SYNTAX = "Syntax for todo\n\t>>> todo <task>";
    public static final String MESSAGE_TOP = "\nGot it. I've added this task:\n" + "\t";
    public String description;

    public ArrayList<Task> task = new ArrayList<Task>();

    @Override
    public CommandRes execute() {
        Task added = new Todo(description);
        TaskList.list.add(added);
        task.add(added);
        return new CommandRes(MESSAGE_TOP, task, TaskList.getAllMessage());
    }
}

