package duke.command;

import duke.ui.Ui;

public class FindCommand extends Command{
    String item;
    public FindCommand(String item) {
        this.item = item;
    }
    public void execute() {
        Ui.displayFind(item);
    }
}
