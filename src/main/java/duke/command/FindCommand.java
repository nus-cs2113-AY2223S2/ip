package duke.command;

import duke.data.TaskList;
import duke.ui.*;

public class FindCommand extends Command{
    private String find;
    public void setFind(String input) {
        String[] temp = input.split(" ", 2);
        this.find = temp[1];
    }
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setFind(input);
            ui.findTask(tasks, find);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyFindMessage();
        }
    }
}
