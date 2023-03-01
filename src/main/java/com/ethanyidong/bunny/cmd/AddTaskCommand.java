package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.fmt.Formatter;
import com.ethanyidong.bunny.task.Task;

/**
 * An intermediate implementation of <code>ExecutableCommand</code> which all commands
 * that create a new <code>Task</code> inherit from
 */
public abstract class AddTaskCommand extends ExecutableCommand {
    protected String name;

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.name = command.getPositionalArgument();
    }

    private void addTask(BunnySession bunny, Task newTask) {
        bunny.getTasks().addTask(newTask);
        bunny.getUI().printMessage("I've added this task: " + newTask + "\nNow you have "
                + bunny.getTasks().numTasks() + " "
                + Formatter.pluralize("task", "tasks", bunny.getTasks().numTasks())
                + " in the list.");
    }

    protected abstract Task generateTask();

    /**
     * Adds the <code>Task</code> represented by the command to the current task list
     * @param bunny the current Bunny session
     */
    public void execute(BunnySession bunny) {
        addTask(bunny, generateTask());
    }
}
