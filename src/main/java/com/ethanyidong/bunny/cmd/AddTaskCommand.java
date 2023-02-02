package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.task.Task;
import com.ethanyidong.bunny.fmt.Formatter;

public abstract class AddTaskCommand extends ExecutableCommand {
    private String name;

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.name = command.getPositionalArgument();
    }

    private void addTask(BunnySession bunny, Task newTask) {
        bunny.addTask(newTask);
        bunny.printMessage("I've added this task: " + newTask + "\nNow you have "
                + bunny.numTasks() + " " + Formatter.pluralize("task", "tasks", bunny.numTasks())
                + " in the list.");
    }

    protected abstract Task generateTask();

    public void execute(BunnySession bunny) {
        addTask(bunny, generateTask());
    }
}
