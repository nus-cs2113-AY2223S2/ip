package Duke.command;

import static Duke.Strings.LINE_SEPARATOR;

import Duke.DukeException;
import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeDeadline;
import Duke.task.DukeTaskList;

/**
 * DukeDeadlineCommand is the class that deals with the add deadline command.
 */
public class DukeDeadlineCommand extends DukeCommand {

    private String[] description;

    /**
     * Constructor for DukeDeadlineCommand.
     * 
     * @param description the description of the deadline
     * @throws DukeException if the description is not in the correct format
     */
    public DukeDeadlineCommand(String description) throws DukeException {
        this.description = description.split("/by", 2);
        if (this.description.length < 2) {
            throw new DukeException("Please enter a valid deadline.");
        }
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        tasks.tasksList.add(new DukeDeadline(description[0].trim(), description[1].trim()));
        System.out.println("Got it. I've added this task:" + LINE_SEPARATOR + "  "
                + tasks.tasksList.get(tasks.tasksList.size() - 1) + LINE_SEPARATOR
                + "Now you have " + tasks.tasksList.size() + " tasks in the list.");
    }

}
