package duke.command;

import duke.ui.Ui;
/**
 * <code>HelpCommand</code> object represents a command that executes
 * and display Help commands
 */
public class HelpCommand extends Command{

    public HelpCommand() {
    }
    public void execute() {
        Ui.sayHi();
    }
}
