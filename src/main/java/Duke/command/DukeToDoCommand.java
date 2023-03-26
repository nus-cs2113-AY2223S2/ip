package Duke.command;

import static Duke.string.Strings.LINE_SEPARATOR;

import Duke.DukeException;
import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeTaskList;
import Duke.task.DukeToDo;

/**
 * DukeDeadlineCommand is the class that deals with the add deadline command.
 */
public class DukeToDoCommand extends DukeCommand {

    private String description;

    /**
     * Constructor for DukeDeadlineCommand.
     * 
     * @param description the description of the deadline
     * @throws DukeException if the description is not in the correct format
     */
    public DukeToDoCommand(String description) throws DukeException {
        this.description = description.trim();
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        tasks.tasksList.add(new DukeToDo(description));
        System.out.println("Got it. I've added this task:" + LINE_SEPARATOR + "  "
                + tasks.tasksList.get(tasks.tasksList.size() - 1) + LINE_SEPARATOR
                + "Now you have " + tasks.tasksList.size() + " tasks in the list.");
    }

}
