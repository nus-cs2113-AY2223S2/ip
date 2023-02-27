package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Event;
import duke.tasktypes.Task;

public class EventCommand {
    private static final String ADDING_TASK = "Got it. I've added this task:";

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
