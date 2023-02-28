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
    private String beginTime;
    private String endTime;

    /**
     * Constructs a new EventCommand object with the given content, beginning time and end time,
     * and adds a new Event task to the task list.
     * @param content The content of an Event task.
     * @param beginTime The beginning time of an Event task.
     * @param endTime The end time of an Event task.
     */
    public EventCommand(String content, String beginTime, String endTime) {
        this.content = content;
        this.beginTime = beginTime;
        this.endTime = endTime;
        addEventTask();
    }

    private void addEventTask() {
        Task task = new Event(content, beginTime, endTime);
        Common.tasks.add(task);
        System.out.println(ADDING_TASK);
        System.out.println(task.formatTaskToPrint());
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        Common.dataFile.appendTaskToDataFile(task.putInputToDataFile());
    }

}
