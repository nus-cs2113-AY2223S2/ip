package Command;

import CommandUtils.InputParser;
import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

/**
 * Command to find all tasks with specified substring in task description
 */
public class FindCommand extends Command implements InputParser {
    String taskSubstring;

    /**
     * Constructor for FindCommand class
     * @param input User Input
     * @throws DukeException
     */
    public FindCommand(String input) throws DukeException {
        parseInput(null, input);
    }

    /**
     * Prints filtered tasks with substring in task description
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        TaskList filteredTasks = tasks.getFilteredTasks(taskSubstring);
        String header = "Here are the tasks I found containing : " + taskSubstring;
        ui.printTasks(filteredTasks, header);
    }

    /**
     * Parses the user input to get the task substring to filter task list
     */
    @Override
    public void parseInput(String command, String input) throws DukeException {
        this.taskSubstring = input.split(" ")[1];
    }
    
}
