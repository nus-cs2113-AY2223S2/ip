package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Deadline;
import duke.tasktypes.Task;

/**
 * Represents the command to add a new Deadline task to the task list.
 */
public class DeadlineCommand {

    private static final String ADDING_TASK = "Got it. I've added this task:";

    private String content;
    private String date;

    /**
     * Constructs a new DeadlineCommand object with the given content and date,
     * and adds a new Deadline task to the task list.
     * @param content The content of a Deadline task.
     * @param date The date of a Deadline task.
     */
    public DeadlineCommand(String content, String date) {
        this.content = content;
        this.date = date;
        addDeadlineTask();
    }

    private void addDeadlineTask() {
        Task task = new Deadline(content,date);
        Common.tasks.add(task);
        System.out.println(ADDING_TASK);
        task.printTask();
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        Common.dataFile.appendTaskToDataFile(task.putInputToDataFile());
    }

}
