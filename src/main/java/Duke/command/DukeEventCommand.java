package Duke.command;

import static Duke.string.Strings.LINE_SEPARATOR;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Duke.DukeException;
import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeEvent;
import Duke.task.DukeTaskList;

/**
 * DukeEventCommand is the class that deals with the add event command.
 */
public class DukeEventCommand extends DukeCommand {

    private String name;
    private String from;
    private String to;

    /**
     * Constructor for DukeEventCommand.
     * 
     * @param description the description of the event
     * @throws DukeException if the description is not in the correct format
     */
    public DukeEventCommand(String description) throws DukeException {
        if (description.matches("(.+?)(?:/from)(.+?)(?:/to)(.+?)")) {
            try {
                Pattern pattern = Pattern.compile("(.+?)(?:/from)(.+?)(?:/to)(.+?)");
                Matcher matcher = pattern.matcher(description);
                if (matcher.matches()) {
                    MatchResult result = matcher.toMatchResult();
                    name = result.group(1);
                    from = result.group(2);
                    to = result.group(3);
                } else {
                    throw new DukeException("Please fill all the parameters.");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            throw new DukeException("Please fill all the parameters.");
        }
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        tasks.tasksList.add(new DukeEvent(name, from, to));
        System.out.println("Got it. I've added this task:" + LINE_SEPARATOR + "  "
                + tasks.tasksList.get(tasks.tasksList.size() - 1) + LINE_SEPARATOR + "Now you have "
                + tasks.tasksList.size() + " tasks in the list.");
    }

}
