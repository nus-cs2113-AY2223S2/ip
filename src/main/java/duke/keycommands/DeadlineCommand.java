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
    private String time;

    /**
     * Constructs a new DeadlineCommand object with the given content and time,
     * and adds a new Deadline task to the task list.
     * @param content The content of a Deadline task.
     * @param time The time of a Deadline task.
     */
    public DeadlineCommand(String content, String time) {
        this.content = content;
        this.time = time;
        addDeadlineTask();
    }

    private void addDeadlineTask() {
        Task task = new Deadline(content,time);
        Common.tasks.add(task);
        System.out.println(ADDING_TASK);
        task.printTask();
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        Common.dataFile.appendTaskToDataFile(task.putInputToDataFile());
    }

}
