package psyduck.command;

import psyduck.parser.Parser;
import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;
public abstract class Command {

    protected Parser parser = new Parser();
    public abstract void executeCommand(String input, TaskList tasks, Ui ui);
}


