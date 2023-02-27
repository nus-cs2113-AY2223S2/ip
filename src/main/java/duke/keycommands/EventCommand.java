package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Event;
import duke.tasktypes.Task;

public class EventCommand {
    public static final String INVALID_INPUT = "This is an invalid input, please follow this input format\n";
    private static final String EVENT_FORMAT = "Format: event {your task} /from {begin date} /to {end date}\n";
    public static final String EVENT_INVALID_INPUT = INVALID_INPUT + EVENT_FORMAT;
    private static final String ADDING_TASK = "Got it. I've added this task:";
    public static final String EMPTY_EVENT_DESCRIPTION = "OOPS!!! The description of a event cannot be empty.";
    private String content;
    private String beginDate;
    private String endDate;

    public EventCommand(String content, String beginDate, String endDate) {
        this.content = content;
        this.beginDate = beginDate;
        this.endDate = endDate;
        addEventTask();
    }
    //add event task
    private void addEventTask() {
        Task task = new Event(content, beginDate, endDate);
        Common.tasks.add(task);
        System.out.println(ADDING_TASK);
        task.printTask();
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        Common.dataFile.appendTaskToDataFile(task.putInputToDataFile());
    }

}
