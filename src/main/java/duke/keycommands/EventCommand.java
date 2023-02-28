package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Event;
import duke.tasktypes.Task;

/**
 * Represents the command to add a new Event task to the task list.
 */
public class EventCommand {
    private static final String ADDING_TASK = "Got it. I've added this task:";

    private String content;
    private String beginDate;
    private String endDate;

    /**
     * Constructs a new EventCommand object with the given content, begin date and end date,
     * and adds a new Event task to the task list.
     * @param content The content of an Event task.
     * @param beginDate The beginning date of an Event task.
     * @param endDate The end date of an Event task.
     */
    public EventCommand(String content, String beginDate, String endDate) {
        this.content = content;
        this.beginDate = beginDate;
        this.endDate = endDate;
        addEventTask();
    }

    private void addEventTask() {
        Task task = new Event(content, beginDate, endDate);
        Common.tasks.add(task);
        System.out.println(ADDING_TASK);
        task.printTask();
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        Common.dataFile.appendTaskToDataFile(task.putInputToDataFile());
    }

}
