package duke.command;

import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }
    public void execute() {
        Ui.displayList();
    }
}
