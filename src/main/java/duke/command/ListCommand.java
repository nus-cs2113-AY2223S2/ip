package duke.command;

import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
    }

    public void execute() {
        Ui.displayList();
    }
}
