package Arsdorint.command;

import Arsdorint.data.TaskList;
import Arsdorint.task.Event;
import Arsdorint.task.Task;

import java.util.ArrayList;

public class CommandEvent extends Command {
    public CommandEvent(String description, String date) {
        super(COMMAND_NAME);
        this.description = description;
        this.date = date;
    }

    public static final String COMMAND_NAME = "event";
    public static final String SYNTAX = "Syntax for event\n\t>>> event <task> /<date of event";;
    public static final String MESSAGE_TOP = "\nGot it. I've added this task:\n" + "\t";
    public String description;
    public String date;
    public ArrayList<Task> task = new ArrayList<Task>();

    /**
     * Execution of the event command
     *
     * @return printing the event's added status to the user
     *
     */
    @Override
    public CommandRes execute() {
        Task added = new Event(description, date);
        TaskList.list.add(added);
        task.add(added);
        return new CommandRes(MESSAGE_TOP, task, TaskList.getAllMessage());
    }
}
