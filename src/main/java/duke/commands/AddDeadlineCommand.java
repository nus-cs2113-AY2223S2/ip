package duke.commands;

import duke.data.Deadline;

/**
 * Adds a deadline to the TaskList.
 */
public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "OOPS!! The correct format of adding deadline is: deadline task /by time";
    private final Deadline toAdd;

    /**
     * Convenience constructor using raw values.
     * @param content the description of the deadline
     * @param byTime the by time of the deadline
     */
    public AddDeadlineCommand(String content, String byTime){
        this.toAdd = new Deadline(content, byTime);
    }

    public void execute() {
        taskList.addTask(toAdd);
        System.out.println("Got it. I've added this task: ");
        System.out.println(toAdd);
        taskList.printSize();
    }
}
