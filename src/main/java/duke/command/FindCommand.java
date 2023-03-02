package duke.command;
import duke.ui.Ui;
/**
 * <code>FindCommand</code> object represents a command that executes
 * the finding of keywords within all Tasks in TaskList and displays
 * such Tasks containing keywords to User
 */
public class FindCommand extends Command{
    String item;
    public FindCommand(String item) {
        this.item = item;
    }
    public void execute() {
        Ui.displayFind(item);
    }
}
