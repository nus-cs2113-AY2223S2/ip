package duke.command;

import duke.ui.Ui;

public class HelpCommand extends Command{

    public HelpCommand() {
    }
    public void execute() {
        Ui.sayHi();
    }
}
