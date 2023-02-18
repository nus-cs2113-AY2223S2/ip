package Command;

import CommandUtils.InputParser;
import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

public class FindCommand extends Command implements InputParser {
    String taskSubstring;

    public FindCommand(String input) throws DukeException {
        parseInput(null, input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        TaskList filteredTasks = tasks.getFilteredTasks(taskSubstring);
        ui.printTasks(filteredTasks);
    }

    @Override
    public void parseInput(String command, String input) throws DukeException {
        this.taskSubstring = input.split(" ")[1];
    }
    
}
