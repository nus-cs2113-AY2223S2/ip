package duke.commands;

import duke.file.TaskList;
import duke.ui.*;
import duke.outputs.Messages;

public class FindCommand extends Command{
    private String keyword;
    public void setFind(String input) {
        String[] deconstructedDetails = input.split(" ", 2);
        this.keyword = deconstructedDetails[1];
    }
    @Override
    public void execute(String input, TaskList tasks, UI ui) {
        try {
            setFind(input);
            ui.printFindTaskMessage(tasks, keyword);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyFindErrorMessage();
        }
    }
}
